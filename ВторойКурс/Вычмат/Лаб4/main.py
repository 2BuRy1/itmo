import sys
import threading
import os
import numpy as np
import matplotlib.pyplot as plt

import solution.resolver as solver
from file_manager import file_reader
from gui_manager.gui_manager import start
from gui_manager.gui_manager import plot_function


def read_xy_console():
    while True:
        try:
            n = int(input("Сколько точек (8–11)? ").strip())
            if not 8 <= n <= 11:
                raise ValueError("Количество точек должно быть от 8 до 11.")
            break
        except ValueError as e:
            print(f"Ошибка: {e}")

    def read_column(name):
        while True:
            raw = input(f"Введите {n} чисел для {name} через пробел: ").strip().split()
            try:
                if len(raw) != n:
                    raise ValueError(f"Нужно ровно {n} значений.")
                return [float(v.replace(",", ".")) for v in raw]
            except ValueError as e:
                print(f"Ошибка: {e}")

    x_vals = read_column("X")
    y_vals = read_column("Y")
    return x_vals, y_vals


def process_output(x_vals, y_vals, results):
    best_method = None
    best_rmse = float('inf')

    output = ""
    for method, data in results.items():
        if data is None:
            output += f"Метод: {method} — невозможно аппроксимировать (некорректные значения)\n"
            output += "-" * 60 + "\n"
            continue

        coeffs, equation, phi_array, epsilons, rmse, r_squared, interpretation, *rest = data

        output += f"Метод: {method}\n"
        output += f"Уравнение: {equation}\n"
        output += f"RMSE: {rmse:.4f}\n"
        output += f"R²: {r_squared:.4f} — {interpretation}\n"

        if method == "Линейная":
            pearson, pearson_interp = rest
            output += f"Коэфф. Пирсона: {pearson:.4f} — {pearson_interp}\n"

        output += "-" * 60 + "\n"

        if rmse < best_rmse:
            best_rmse = rmse
            best_method = method

    if best_method:
        output += f"\nРекомендуемый метод: {best_method} (наименьший RMSE = {best_rmse:.4f})\n"

    while True:
        try:
            out_m = get_valid_input(
                "\nВыберите способ вывода (1 - консоль, 2 - файл): ",
                lambda v: v if v in ("1", "2") else ValueError())
            if out_m == "1":
                plot_function(x_vals, y_vals, results)
                print("\nРезультат:\n", output)

            else:
                while True:
                    fname = input("Имя файла: ").strip()
                    if not fname:
                        print("Имя не может быть пустым.")
                        continue
                    try:
                        with open(fname, "w", encoding="utf-8") as f:
                            f.write(str(output))
                        print(f"Сохранено в {fname}")
                        break
                    except Exception as e:
                        print(f"Ошибка записи: {e}")

            ask_for_leave = input("Хотите выйти из приложения? Напишите exit:")

            if ask_for_leave == "exit":
                os._exit(0)
            break
        except Exception as e:
            print(f"Ошибка: {e}")


def get_valid_input(prompt, validate):
    while True:
        try:
            v = input(prompt).strip()
            if not v:
                raise ValueError("Поле не может быть пустым.")
            return validate(v)
        except ValueError as e:
            print(f"Ошибка ввода: {e}")


def console_input():
    while True:
        cmd = get_valid_input("\nКоманда (solve / exit): ",
                              lambda x: x if x in ("solve", "exit") else ValueError())
        if cmd == "exit":
            print("Выход…")
            os._exit(0)
        process_console_solution()
        main()

def process_console_solution():
    while True:
        try:
            x_values, y_values = read_xy_console()

            results = solver.calculate_interpolations(x_values, y_values)


            process_output(x_values, y_values ,results)



            break

        except Exception as e:
            print(f"Ошибка: {e}. Попробуйте снова.")





def main():
    while True:
        method = file_reader.get_input_method()
        if method == "1":
            while True:
                try:
                    x_vals, y_vals = file_reader.read_input_from_file()
                    result = solver.calculate_interpolations(x_vals, y_vals)
                    process_output(x_vals, y_vals, result)
                    break
                except Exception as e:
                    print(f"Ошибка файла: {e}")
        else:
            console_input()



if __name__ == "__main__":
    threading.Thread(target=main, daemon=True).start()
    start()
    print("meow")