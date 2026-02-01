import math
import tkinter as tk
from tkinter import messagebox
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2Tk
from solution import resolver as solution

def get_input_data():
    global x_values, y_values
    x_values = []
    y_values = []

    filled_x = 0
    filled_y = 0

    for i in range(11):
        x_entry = entries_x[i].get()
        y_entry = entries_y[i].get()
        if x_entry:
            filled_x += 1
            try:
                x_values.append(float(x_entry))
            except ValueError:
                messagebox.showerror("Ошибка", f"Некорректное значение X в строке {i + 1}")
                return None, None

        if y_entry:
            filled_y += 1
            try:
                y_values.append(float(y_entry))
            except ValueError:
                messagebox.showerror("Ошибка", f"Некорректное значение Y в строке {i + 1}")
                return None, None

    if filled_x < 8 or filled_y < 8:
        messagebox.showerror("Ошибка", "Необходимо заполнить минимум 8 значений в каждом столбце")
        return None, None

    if filled_x != filled_y:
        messagebox.showerror("Ошибка", f"Количество X ({filled_x}) и Y ({filled_y}) значений не совпадает")
        return None, None

    return x_values, y_values



def plot_function(x_values, y_values, results):
    for w in frame_plot.winfo_children():
        w.destroy()

    pts = sorted(zip(x_values, y_values), key=lambda p: p[0])
    xs, ys = zip(*pts)

    fig, ax = plt.subplots(figsize=(8, 5))

    ax.scatter(xs, ys, color="black", zorder=3, label="Данные")

    x_min, x_max = min(xs), max(xs)
    x_dense = [x_min + (x_max - x_min) * i / 400 for i in range(401)]

    all_y = list(ys)

    for name, data in results.items():
        if data is None:
            continue

        coeffs, equation, _, *_ = data

        if   name == "Линейная":
            a, b = coeffs
            y_dense = [a + b*x for x in x_dense]
        elif name == "Квадратичная":
            a, b, c = coeffs
            y_dense = [a + b*x + c*x*x for x in x_dense]
        elif name == "Кубическая":
            a, b, c, d = coeffs
            y_dense = [a + b*x + c*x*x + d*x**3 for x in x_dense]
        elif name == "Экспоненциальная":
            a, b = coeffs
            y_dense = [a * math.exp(b*x) for x in x_dense]
        elif name == "Логарифмическая":
            a, b = coeffs
            y_dense = [a + b*math.log(x) if x > 0 else float("nan") for x in x_dense]
        elif name == "Степенная":
            a, b = coeffs
            y_dense = [a * (x**b) if x > 0 else float("nan") for x in x_dense]
        else:
            continue

        ax.plot(x_dense, y_dense, label=name)
        all_y.extend(yy for yy in y_dense if not math.isnan(yy))

    y_min, y_max = min(all_y), max(all_y)
    pad = 0.15 * (y_max - y_min) if y_max != y_min else 1
    ax.set_ylim(y_min - pad, y_max + pad)

    ax.set_xlim(x_min, x_max)
    ax.grid(True, zorder=0)
    ax.set_title("Сравнение методов аппроксимации")
    ax.legend(fontsize=9)

    canvas = FigureCanvasTkAgg(fig, master=frame_plot)
    canvas.draw()
    canvas.get_tk_widget().pack(fill="both", expand=True)
    NavigationToolbar2Tk(canvas, frame_plot).update()


def setData():
    x_values, y_values = get_input_data()
    if x_values is None or y_values is None:
        return

    try:
        results = solution.calculate_interpolations(x_values, y_values)

        result_text.delete("1.0", tk.END)

        best_method = None
        best_rmse = float('inf')

        for method, data in results.items():
            if data is None:
                result_text.insert(tk.END, f"Метод: {method} — невозможно аппроксимировать (некорректные значения)\n")
                result_text.insert(tk.END, "-" * 60 + "\n")
                continue

            coeffs, equation, phi_array, epsilons, rmse, r_squared, interpretation, *rest = data

            result_text.insert(tk.END, f"Метод: {method}\n")
            result_text.insert(tk.END, f"Уравнение: {equation}\n")
            result_text.insert(tk.END, f"RMSE: {rmse:.4f}\n")
            result_text.insert(tk.END, f"R²: {r_squared:.4f} — {interpretation}\n")

            if method == "Линейная":
                pearson = rest[0]
                pearson_interp = rest[1]
                result_text.insert(tk.END, f"Коэфф. Пирсона: {pearson:.4f} — {pearson_interp}\n")

            result_text.insert(tk.END, "-" * 60 + "\n")

            if rmse < best_rmse:
                best_rmse = rmse
                best_method = method

        if best_method:
            result_text.insert(tk.END, f"\nРекомендуемый метод: {best_method} (наименьший RMSE = {best_rmse:.4f})")

        plot_function(x_values, y_values, results)

    except Exception as e:
        result_text.delete("1.0", tk.END)
        result_text.insert(tk.END, f"Ошибка: {e}\n")
root = tk.Tk()
root.title("Интерполяция")
root.geometry("1000x700")

frame_controls = tk.Frame(root)
frame_controls.pack(pady=10)

input_frame = tk.Frame(root)
input_frame.pack(pady=10)

tk.Label(input_frame, text="Точка", font=('Arial', 10, 'bold')).grid(row=0, column=0)
tk.Label(input_frame, text="X", font=('Arial', 10, 'bold')).grid(row=0, column=1)
tk.Label(input_frame, text="Y", font=('Arial', 10, 'bold')).grid(row=0, column=2)

entries_x = []
entries_y = []

for i in range(1, 12):
    tk.Label(input_frame, text=f"{i}").grid(row=i, column=0, padx=5, pady=2)

    entry_x = tk.Entry(input_frame, width=2)
    entry_x.grid(row=i, column=1, padx=5, pady=2)
    entries_x.append(entry_x)

    entry_y = tk.Entry(input_frame, width=2)
    entry_y.grid(row=i, column=2, padx=5, pady=2)
    entries_y.append(entry_y)


for i in range(12, 24):
    tk.Label(input_frame, text=f"{i}").grid(row=i, column=0, padx=5, pady=2)

    entry_x = tk.Entry(input_frame, width=2)
    entry_x.grid(row=i, column=3, padx=5, pady=2)
    entries_x.append(entry_x)

    entry_y = tk.Entry(input_frame, width=2)
    entry_y.grid(row=i, column=4, padx=5, pady=2)
    entries_y.append(entry_y)




frame_plot = tk.Frame(root)
frame_plot.pack(pady=10)

solve_button = tk.Button(frame_controls, text="Построить график", command=setData)
solve_button.pack()

result_text = tk.Text(frame_controls, height=10, width=80, wrap="word")
result_text.pack()


def start():
    root.mainloop()

