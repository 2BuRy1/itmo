import tkinter as tk
from tkinter import ttk, messagebox
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2Tk
import numpy as np

from solution.solution import (
    make_grid, improved_euler_until_eps,
    rk4_until_eps, milne,
)



global result_text
result_text = None

FUNCTIONS = {
    "y' = y + x":      lambda x, y: y + x,
    "y' = x * y":      lambda x, y: x * y,
}

EXACT = {
    "y' = y + x":      lambda x, y0, x0: np.exp(x - x0) * (y0 + x0 + 1) - x - 1,
    "y' = x * y":      lambda x, y0, x0: y0 * np.exp((x ** 2 - x0 ** 2) / 2),
}



FUNCTIONS.update({
    "y' = y": lambda x, y: y,
    "y' = x": lambda x, y: x,
    "y' = 2x + 1": lambda x, y: 2 * x + 1,
    "y' = cos(x)": lambda x, y: np.cos(x),
})

EXACT.update({
    "y' = y": lambda x, y0, x0: y0 * np.exp(x - x0),
    "y' = x": lambda x, y0, x0: y0 + (x**2 - x0**2) / 2,
    "y' = 2x + 1": lambda x, y0, x0: y0 + (x**2 - x0**2) + (x - x0),
    "y' = cos(x)": lambda x, y0, x0: y0 + np.sin(x) - np.sin(x0),
})






def solve_and_show():
    try:
        y0, x0, xn, h, eps = map(float, (e_y0.get().replace(",", "."), e_x0.get().replace(",", "."),
                                         e_xn.get().replace(",", "."), e_h.get().replace(",", "."), e_eps.get().replace(",", ".")))
    except ValueError:
        messagebox.showerror("Ошибка", "Неверный ввод")
        return

    fn = cmb.get()
    if fn not in FUNCTIONS or x0 >= xn or h <= 0 or eps <= 0:
        messagebox.showerror("Ошибка", "Проверьте входные данные")
        return

    f = FUNCTIONS[fn]
    exact_fn = EXACT[fn]

    xs = make_grid(x0, xn, h)

    if len(xs) < 2:
        print(f"Ошибка: шаг h = {h} слишком большой, интервал [{x0}, {xn}] содержит только одну точку.")
        return
    if len(xs) < 4:
        print(f"Ошибка: метод Милна требует как минимум 4 точки, а сейчас только {len(xs)}.")
        return

    y_exact = [exact_fn(x, y0, x0) for x in xs]

    xs_eul, y_eul, err_e = improved_euler_until_eps(f, x0, xn, y0, h, eps)
    xs_rk, y_rk, err_rk = rk4_until_eps(f, x0, xn, y0, h, eps)
    y_m = milne(f, xs, y0, eps)
    err_m = [abs(ye - ym) if np.isfinite(ye) else np.nan for ye, ym in zip(y_exact, y_m)]

    def safe(val): return float(val) if np.isfinite(val) else float('nan')



    def append_table_rows(xs_local, y_local, y_exact_local, method_name, err_local):
        txt.insert(tk.END, f"\n{method_name}:\n")
        txt.insert(tk.END, f"{'x':<9}{'y*':>11}{'y':>11}{'err':>11}\n")
        for i in range(len(xs_local)):
            x = xs_local[i]
            y_ex = safe(y_exact_local[i]) if i < len(y_exact_local) else float('nan')
            y_approx = safe(y_local[i])
            err_val = safe(err_local[i]) if i < len(err_local) else float('nan')
            txt.insert(tk.END, f"{x:<9.4f}{y_ex:>11.4f}{y_approx:>11.4f}{err_val:>11.4f}\n")

    txt.delete("1.0", tk.END)

    append_table_rows(xs_eul, y_eul, [exact_fn(x, y0, x0) for x in xs_eul], "Improved Euler", err_e)
    append_table_rows(xs_rk,  y_rk,  [exact_fn(x, y0, x0) for x in xs_rk],  "Runge-Kutta 4", err_rk)
    append_table_rows(xs,     y_m,   y_exact,                               "Milne",         err_m)


    for w in plot_frm.winfo_children():
        w.destroy()
    root.update_idletasks()

    plt.close("all")
    fig, ax = plt.subplots(figsize=(7, 4)); ax.grid(True)
    ax.plot(xs,     y_exact, 'k--', lw=2, label='Exact')
    ax.plot(xs_eul, y_eul,   'r-',  lw=2, label='Euler')
    ax.plot(xs_rk,  y_rk,    'g--', lw=1.5, label='RK4')
    ax.plot(xs,     y_m,     'b-.', lw=1.5, label='Milne')
    ax.set_xlabel('x'); ax.set_ylabel('y'); ax.legend()
    fig.tight_layout()

    cvs = FigureCanvasTkAgg(fig, plot_frm)
    cvs.draw(); cvs.get_tk_widget().pack(expand=True, fill='both')
    NavigationToolbar2Tk(cvs, plot_frm).update()


root = tk.Tk()
root.title("Численные методы")
root.geometry("1100x760")

frm = tk.Frame(root); frm.pack(pady=8, fill='x')
tk.Label(frm, text="Уравнение:").grid(row=0, column=0, sticky='e')
cmb = ttk.Combobox(frm, width=26, state="readonly", values=list(FUNCTIONS.keys()))
cmb.grid(row=0, column=1, sticky='w'); cmb.current(0)

labels = ("y0", "x0", "xn", "h", "ε")
ents = []
for i, l in enumerate(labels, 1):
    tk.Label(frm, text=l).grid(row=i, column=0, sticky='e')
    e = tk.Entry(frm, width=10); e.grid(row=i, column=1, sticky='w')
    ents.append(e)

e_y0, e_x0, e_xn, e_h, e_eps = ents
for e, v in zip(ents, ("1", "0", "1", "0.1", "1e-4")):
    e.insert(0, v)

tk.Button(frm, text="Solve", command=solve_and_show).grid(row=6, column=1, pady=6, sticky='w')


result_text = tk.Text(root, height=15, width=120, wrap='none')
result_text.pack(fill='x')
txt = result_text
plot_frm = tk.Frame(root); plot_frm.pack(expand=True, fill='both')


def start_gui_plot(xs, y_exact, xs_eul, y_eul, xs_rk, y_rk, xs_milne, y_milne):
    for w in plot_frm.winfo_children():
        w.destroy()

    plt.close("all")
    fig, ax = plt.subplots(figsize=(7, 4)); ax.grid(True)
    ax.plot(xs,      y_exact,  'k--', lw=2, label='Exact')
    ax.plot(xs_eul,  y_eul,    'r-',  lw=2, label='Euler')
    ax.plot(xs_rk,   y_rk,     'g--', lw=1.5, label='RK4')
    ax.plot(xs_milne, y_milne, 'b-.', lw=1.5, label='Milne')
    ax.set_xlabel('x'); ax.set_ylabel('y'); ax.legend()
    fig.tight_layout()

    cvs = FigureCanvasTkAgg(fig, plot_frm)
    cvs.draw(); cvs.get_tk_widget().pack(expand=True, fill='both')
    NavigationToolbar2Tk(cvs, plot_frm).update()



def start_gui():
    root.mainloop()



def update_result_text(content: str):
    if result_text:
        result_text.delete("1.0", tk.END)
        result_text.insert("1.0", content)