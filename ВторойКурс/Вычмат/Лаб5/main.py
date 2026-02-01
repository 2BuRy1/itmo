import sys
import threading
import os
from file_manager import file_reader
from gui_manager.gui_manager import (
    start, plot_function, get_frame_plot,
    get_func_combobox, get_input_mode
)
from solution.solution import solve

arg = None

def read_xy_console():
    print("Вводите значения X и Y через пробел. Например:\nX: 1 2 3\nY: 4 5 6")
    while True:
        try:
            x_raw = input("Введите значения X: ").strip().split()
            x_vals = [float(v.replace(",", ".")) for v in x_raw]

            y_raw = input("Введите значения Y: ").strip().split()
            y_vals = [float(v.replace(",", ".")) for v in y_raw]

            if len(x_vals) != len(y_vals):
                print(f"Ошибка: количество X ({len(x_vals)}) не совпадает с количеством Y ({len(y_vals)}). Попробуйте снова.")
                continue

            if len(x_vals) < 2:
                print("Ошибка: нужно минимум 2 точки.")
                continue

            return x_vals, y_vals
        except ValueError:
            print("Ошибка: все значения должны быть числами. Попробуйте снова.")

def process_output(x_vals, y_vals, result):
    arg = result["arg"]
    output = ""
    output += result['Таблица конечных разностей'] + "\n"
    output += f"Ньютон({arg}) = {result['Интерполяция Ньютона']}\n"
    output += f"Лагранж({arg}) = {result['Интерполяция Лагранжа']}\n"
    output += f"Гаусс({arg}) = {result['Интерполяция Гаусса']}\n"

    print("\nРезультат:\n", output)

    # Добавляем вывод в текстовое поле GUI
    from gui_manager.gui_manager import get_result
    try:
        get_result().delete("1.0", "end")
        get_result().insert("1.0", output)
    except Exception as e:
        print(f"GUI вывод не удался: {e}")

    plot_function(
        x_vals, y_vals, result, arg,
        get_frame_plot(),
        get_func_combobox().get() if get_input_mode().get() == "function" else None
    )

    ask_for_leave = input("Хотите выйти из приложения? Напишите exit: ")
    if ask_for_leave.lower() == "exit":
        os._exit(0)

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
            global arg
            while True:
                try:
                    arg = float(input("Введите аргумент для интерполяции: ").replace(",", "."))
                    break
                except ValueError as e:
                    print(f"Ошибка: {e}")
            result = solve(x_values, y_values, arg)
            process_output(x_values, y_values, result)
            break
        except Exception as e:
            print(f"Ошибка: {e}. Попробуйте снова.")

def process_function_mode():
    from gui_manager.gui_manager import FUNCTIONS
    print("\nДоступные функции:")
    for name in FUNCTIONS:
        print("-", name)

    func = None
    while func is None:
        fname = input("Выберите функцию по имени: ").strip()
        if fname in FUNCTIONS:
            get_func_combobox().set(fname)
            func = FUNCTIONS[fname]
        else:
            print("Неверное имя функции. Попробуйте снова.")

    while True:
        try:
            start_val = input("Начало интервала: ").strip()
            start = float(start_val.replace(",", "."))
            break
        except ValueError:
            print(f"Ошибка: '{start_val}' не является числом.")

    while True:
        try:
            end_val = input("Конец интервала: ").strip()
            end = float(end_val.replace(",", "."))
            if end <= start:
                print("Ошибка: конец должен быть больше начала.")
                continue
            break
        except ValueError:
            print(f"Ошибка: '{end_val}' не является числом.")

    while True:
        try:
            count_val = input("Количество точек (не менее 2): ").strip()
            count = int(count_val)
            if count < 2:
                print("Ошибка: минимум 2 точки.")
                continue
            break
        except ValueError:
            print(f"Ошибка: '{count_val}' не является целым числом.")

    while True:
        try:
            arg_val = input("Введите аргумент для интерполяции: ").strip()
            global arg
            arg = float(arg_val)
            break
        except ValueError:
            print(f"Ошибка: '{arg_val}' не является числом.")

    x_vals = [start + i * (end - start) / (count - 1) for i in range(count)]
    y_vals = [func(x) for x in x_vals]
    result = solve(x_vals, y_vals, arg)
    get_input_mode().set("function")
    process_output(x_vals, y_vals, result)

def main():
    while True:
        method = file_reader.get_input_method()
        if method == "1":
            console_input()
        elif method == "2":
            process_function_mode()

if __name__ == "__main__":
    threading.Thread(target=main, daemon=True).start()
    start()