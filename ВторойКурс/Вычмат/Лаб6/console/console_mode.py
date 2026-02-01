import os
import numpy as np
from gui_manager.gui_manager import FUNCTIONS, EXACT, start_gui_plot, update_result_text
from solution.solution import (
    make_grid, improved_euler_until_eps, runge_kutta4,
    rk4_until_eps, milne
)

def get_valid_float(prompt):
    while True:
        try:
            return float(input(prompt).replace(",", "."))
        except ValueError:
            print("Ошибка: Введите число.")

def get_valid_positive_float(prompt):
    while True:
        val = get_valid_float(prompt)
        if val <= 0:
            print("Ошибка: Значение должно быть положительным.")
        else:
            return val

def get_equation():
    print("Доступные уравнения:")
    for i, name in enumerate(FUNCTIONS.keys(), start=1):
        print(f"{i}. {name}")
    while True:
        try:
            idx = int(input("Выберите номер уравнения: "))
            if idx < 1 or idx > len(FUNCTIONS):
                raise IndexError
            name = list(FUNCTIONS.keys())[idx - 1]
            return name, FUNCTIONS[name], EXACT[name]
        except (IndexError, ValueError):
            print("Ошибка: Неверный выбор.")

def main_console():
    print("\n--- Консольный режим ---\n")
    eq_name, f, f_exact = get_equation()
    y0 = get_valid_float("Введите y0: ")
    x0 = get_valid_float("Введите x0: ")
    xn = get_valid_float("Введите xn: ")
    if xn <= x0:
        print("Ошибка: xn должно быть больше x0.")
        return
    h = get_valid_positive_float("Введите шаг h: ")
    eps = get_valid_positive_float("Введите точность eps: ")

    def safe(val): return float(val) if np.isfinite(val) else float('nan')

    xs = make_grid(x0, xn, h)
    if len(xs) < 2:
        print(f"Ошибка: шаг h = {h} слишком большой, интервал [{x0}, {xn}] содержит только одну точку.")
        return
    if len(xs) < 4:
        print(f"Ошибка: метод Милна требует как минимум 4 точки, а сейчас только {len(xs)}.")
        return

    y_exact = [f_exact(x, y0, x0) for x in xs]
    xs_eul, y_eul, err_e = improved_euler_until_eps(f, x0, xn, y0, h, eps)
    xs_rk, y_rk, err_rk = rk4_until_eps(f, x0, xn, y0, h, eps)
    y_m = milne(f, xs, y0, eps)
    err_m = [abs(ye - ym) if np.isfinite(ye) else np.nan for ye, ym in zip(y_exact, y_m)]

    output_lines = []

    def append_table(xs_local, y_local, err_local, method_name):
        output_lines.append(f"\n{method_name}:")
        output_lines.append(f"{'x':<9}{'y*':>11}{'y':>11}{'err':>11}")
        for i in range(len(xs_local)):
            x = xs_local[i]
            y_ex = safe(f_exact(x, y0, x0))
            y_approx = safe(y_local[i])
            err = safe(err_local[i]) if i < len(err_local) else float('nan')
            output_lines.append(f"{x:<9.4f}{y_ex:>11.4f}{y_approx:>11.4f}{err:>11.4f}")

    append_table(xs_eul, y_eul, err_e, "Improved Euler")
    append_table(xs_rk,  y_rk,  err_rk, "Runge-Kutta 4")
    append_table(xs,     y_m,   err_m,  "Milne")

    full_output = "\n".join(output_lines)
    print(full_output)

    try:
        update_result_text(full_output)
    except Exception:
        pass
    start_gui_plot(xs, y_exact, xs_eul, y_eul, xs_rk, y_rk, xs, y_m)

def main_console_loop():
    while True:
        cmd = input("\nКоманда (solve / exit): ").strip().lower()
        if cmd == "exit":
            print("Выход…")
            os._exit(0)
        elif cmd == "solve":
            main_console()
        else:
            print("Неизвестная команда.")