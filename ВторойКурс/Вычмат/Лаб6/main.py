import threading
import os
import numpy as np

from console.console_mode import main_console_loop
from gui_manager.gui_manager import FUNCTIONS, EXACT, start_gui_plot, start_gui
from solution.solution import (
    make_grid, improved_euler_until_eps, rk4_until_eps, milne
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
            name = list(FUNCTIONS.keys())[idx - 1]
            return name, FUNCTIONS[name], EXACT[name]
        except (IndexError, ValueError):
            print("Ошибка: Неверный выбор.")


def main_console():
    print("\n--- Консольный режим ---\n")
    eq_name, f, f_exact = get_equation()

    y0 = get_valid_float("Введите y0: ")
    x0 = get_valid_float("Введите x0: ")

    while True:
        try:
            xn = float(input("Введите xn: ").replace(",", "."))
            if xn <= x0:
                print("Ошибка: xn должно быть больше x0.")
            else:
                break
        except ValueError:
            print("Ошибка: Введите число.")

    h = get_valid_positive_float("Введите шаг h: ")
    eps = get_valid_positive_float("Введите точность eps: ")

    xs = make_grid(x0, xn, h)
    y_exact = [f_exact(x, y0, x0) for x in xs]

    xs_eul, y_eul, err_e = improved_euler_until_eps(f, x0, xn, y0, h, eps)
    xs_rk, y_rk, err_rk = rk4_until_eps(f, x0, xn, y0, h, eps)
    y_m = milne(f, xs, y0, eps)
    err_m = [abs(ye - ym) if np.isfinite(ye) else np.nan for ye, ym in zip(y_exact, y_m)]

    print(f"\n{'x':<7}{'y*':>11}{'Euler':>11}{'errE':>9}"
          f"{'RK4':>11}{'errRK':>9}{'Milne':>11}{'errM':>9}")
    for i, x in enumerate(xs):
        def safe(v): return float(v) if np.isfinite(v) else float('nan')
        print(f"{x:<7.3f}{safe(y_exact[i]):>11.3f}{safe(y_eul[i]):>11.3f}{safe(err_e[i]):>9.3f}"
              f"{safe(y_rk[i] if i < len(y_rk) else np.nan):>11.3f}"
              f"{safe(err_rk[i] if i < len(err_rk) else np.nan):>9.3f}"
              f"{safe(y_m[i]):>11.3f}{safe(err_m[i]):>9.3f}")

    start_gui_plot(xs, y_exact, xs_eul, y_eul, xs_rk, y_rk, xs, y_m)
def console_thread():
    while True:
        cmd = input("\nКоманда (solve / exit): ").strip().lower()
        if cmd == "exit":
            print("Выход…")
            os._exit(0)
        elif cmd == "solve":
            main_console()
        else:
            print("Неизвестная команда.")



if __name__ == "__main__":
    threading.Thread(target=main_console_loop, daemon=True).start()

    start_gui()