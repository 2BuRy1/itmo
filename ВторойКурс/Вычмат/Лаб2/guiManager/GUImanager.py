import tkinter as tk
from tkinter import ttk
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2Tk

from solver import Solver

data = None


def plot_function():
    global canvas, ax, toolbar

    for widget in frame_plot.winfo_children():
        widget.destroy()

    fig, ax = plt.subplots()
    x = np.linspace(-3, 3, 800)

    try:
        if selected_type.get() == "Нелинейное уравнение":
            equation = selected_equation.get()

            if not equation:
                raise ValueError("Уравнение не выбрано.")

            if equation == "sin(x) - x/2 = 0":
                y = np.sin(x) - x / 2
            elif equation == "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06 = 0":
                y = 2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06
            elif equation == "e^x - 3 * x = 0":
                y = np.exp(x) - 3 * x
            else:
                raise ValueError("Ошибка: Некорректное уравнение.")

            ax.plot(x, y)
            ax.axhline(0, color='black', linewidth=1)
            ax.axvline(0, color='black', linewidth=1)

        elif selected_type.get() == "Система нелинейных уравнений":
            system = selected_system.get()

            if not system:
                raise ValueError("Ошибка: Система уравнений не выбрана.")

            x = np.linspace(-2, 2, 100)
            y = np.linspace(-2, 2, 100)
            X, Y = np.meshgrid(x, y)

            if system == "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0":
                Z1 = 0.15 * X**2 + 0.25 * Y**2 + X - 0.4
                Z2 = 0.3 * X**2 + 0.2 * X * Y + Y - 0.6
                ax.contour(X, Y, Z1, levels=[0], colors='r')
                ax.contour(X, Y, Z2, levels=[0], colors='b')

            elif system == "2x1 - sin(x2) - 1 = 0, 3x2 - cos(x1) - 2 = 0":
                Z1 = 2 * X - np.sin(Y) - 1
                Z2 = 3 * Y - np.cos(X) - 2
                ax.contour(X, Y, Z1, levels=[0], colors='r')
                ax.contour(X, Y, Z2, levels=[0], colors='b')

            else:
                raise ValueError("Ошибка: Некорректная система уравнений.")

            ax.set_title("График системы уравнений")

        else:
            raise ValueError("Ошибка: Тип задачи не выбран.")

    except Exception as e:
        ax.text(0.5, 0.5, str(e), horizontalalignment='center', verticalalignment='center', fontsize=12, color='red')

    canvas = FigureCanvasTkAgg(fig, master=frame_plot)
    canvas.get_tk_widget().pack()

    toolbar = NavigationToolbar2Tk(canvas, frame_plot)
    toolbar.update()
    canvas.get_tk_widget().pack()
    canvas.draw()

def solve(event=None):
    plot_function()


def update_options(event):
    equation_label.pack_forget()
    equation_menu.pack_forget()
    system_label.pack_forget()
    system_menu.pack_forget()
    method_label.pack_forget()
    method_menu.pack_forget()
    interval_label.pack_forget()
    interval_entry.pack_forget()
    precision_label.pack_forget()
    precision_entry.pack_forget()
    initial_guess_label.pack_forget()
    initial_guess_entry.pack_forget()

    if selected_type.get() == "Нелинейное уравнение":
        equation_label.pack()
        equation_menu.pack()
        method_label.pack()
        method_menu.pack()
        interval_label.pack()
        interval_entry.pack()
        precision_label.pack()
        precision_entry.pack()
    elif selected_type.get() == "Система нелинейных уравнений":
        system_label.pack()
        system_menu.pack()
        precision_label.pack()
        precision_entry.pack()
        initial_guess_label.pack()
        initial_guess_entry.pack()

    plot_function()


def isDataCorrect():
    if selected_type.get() == "Нелинейное уравнение":
        return all([
            selected_equation.get().strip(),
            selected_method_for_unlinear.get().strip(),
            interval_entry.get().strip(),
            precision_entry.get().strip()
        ])
    elif selected_type.get() == "Система нелинейных уравнений":
        return all([
            selected_system.get().strip(),
            precision_entry.get().strip(),
            initial_guess_entry.get().strip()
        ])
    return False

def setData():
    global data

    if not isDataCorrect():
        result_text.delete("1.0", tk.END)
        result_text.insert(tk.END, "Ошибка: Некорректные данные! Проверьте ввод.\n")
        return

    try:
        if selected_type.get() == "Нелинейное уравнение":
            data = False, selected_equation.get(), selected_method_for_unlinear.get(), interval_entry.get(), precision_entry.get()
        else:
            data = True, selected_system.get(), initial_guess_entry.get(), precision_entry.get()

        result = Solver.solve(data)

        result_text.delete("1.0", tk.END)
        result_text.insert(tk.END, f"Результат решения:\n{result}\n")

    except Exception as e:
        result_text.delete("1.0", tk.END)
        result_text.insert(tk.END, f"Ошибка при вычислении: {e}\n")


root = tk.Tk()
root.title("Решение нелинейных уравнений")
root.geometry("1000x700")

frame_controls = tk.Frame(root)
frame_controls.pack(pady=10)

frame_plot = tk.Frame(root)
frame_plot.pack(pady=10)

selected_type = tk.StringVar()
type_label = tk.Label(frame_controls, text="Выберите тип задачи:")
type_label.pack()
type_menu = ttk.Combobox(frame_controls, textvariable=selected_type,
                         values=["Нелинейное уравнение", "Система нелинейных уравнений"])
type_menu.pack()
type_menu.bind("<<ComboboxSelected>>", update_options)

selected_equation = tk.StringVar()
equation_label = tk.Label(frame_controls, text="Выберите уравнение:")
equation_menu = ttk.Combobox(frame_controls, textvariable=selected_equation,
                             values=["sin(x) - x/2 = 0", "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06 = 0", "e^x - 3 * x = 0"])
equation_menu.bind("<<ComboboxSelected>>", solve)

selected_system = tk.StringVar()
system_label = tk.Label(frame_controls, text="Выберите систему уравнений:")
system_menu = ttk.Combobox(frame_controls, textvariable=selected_system,
                           values=["0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0", "2x1 - sin(x2) - 1 = 0, 3x2 - cos(x1) - 2 = 0"])
system_menu.bind("<<ComboboxSelected>>", solve)

selected_method_for_unlinear = tk.StringVar()
method_label = tk.Label(frame_controls, text="Выберите метод решения:")
method_menu = ttk.Combobox(frame_controls, textvariable=selected_method_for_unlinear,
                           values=["Метод простых итераций", "Метод Хорд", "Метод Cекущих"])
method_menu.bind("<<ComboboxSelected>>", solve)

interval_label = tk.Label(frame_controls, text="Введите интервал (пример: -2;2):")
interval_entry = tk.Entry(frame_controls)

precision_label = tk.Label(frame_controls, text="Введите точность (пример: 0.001):")
precision_entry = tk.Entry(frame_controls)

initial_guess_label = tk.Label(frame_controls, text="Введите начальное приближение (пример: 0.5; 0.5):")
initial_guess_entry = tk.Entry(frame_controls)

solve_button = tk.Button(frame_controls, text="Отправить", command=setData)
solve_button.pack()


result_text = tk.Text(frame_controls, height=10, width=80, wrap="word")
result_text.pack()


def start():
    root.mainloop()