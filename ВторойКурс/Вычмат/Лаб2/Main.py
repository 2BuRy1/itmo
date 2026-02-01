import threading
from solver import Solver
from fileReader import FileInputManager
from guiManager.GUImanager import start
import matplotlib.pyplot as plt
import numpy as np


def main():
    while True:
        method = FileInputManager.get_input_method()

        if method == "1":
            while True:
                try:
                    file_data = FileInputManager.read_input_from_file()
                    result = Solver.solve(file_data)
                    print(result)
                    process_output(result)
                    break
                except Exception as e:
                    print(f"Ошибка обработки файла: {e}. Попробуйте снова.")

        elif method == "2":
            console_thread = threading.Thread(target=console_input, daemon=True)
            console_thread.start()
            start()


import os


def process_output(result):
    while True:
        try:
            output_method = get_valid_input(
                "\nВыберите способ вывода результата (1 - консоль, 2 - файл): ",
                lambda x: x if x in ["1", "2"] else ValueError("Введите 1 или 2.")
            )

            if output_method == "1":
                print("\nРезультат:")
                print(result)
            elif output_method == "2":
                while True:
                    file_name = input("Введите имя файла для сохранения результата: ").strip()


                    if not os.path.exists(file_name):
                        try:
                            open(file_name, "w").close()  # Создаём пустой файл
                            print(f"Создан новый файл: {file_name}")
                        except Exception as e:
                            print(f"Ошибка создания файла: {e}. Попробуйте снова.")
                            continue


                    try:
                        with open(file_name, "w", encoding="utf-8") as file:
                            file.write(str(result))
                        print(f"Результат успешно сохранен в файл: {file_name}")
                        break
                    except Exception as e:
                        print(f"Ошибка записи в файл: {e}. Попробуйте снова.")

            break
        except Exception as e:
            print(f"Ошибка: {e}. Попробуйте снова.")
def console_input():
    while True:
        try:
            command = get_valid_input("\nВведите команду (solve - решить, exit - выйти): ",
                                      lambda x: x if x in ["solve", "exit"] else ValueError("Введите 'solve' или 'exit'."))

            if command == "exit":
                print("Выход из программы...")
                return
            elif command == "solve":
                process_console_solution()
        except Exception as e:
            print(f"Ошибка выполнения команды: {e}. Попробуйте снова.")


def process_console_solution():
    while True:
        try:
            task_type = get_valid_input(
                "Выберите тип задачи (1 - Нелинейное уравнение, 2 - Система нелинейных уравнений): ",
                lambda x: x if x in ["1", "2"] else ValueError("Введите 1 или 2.")
            )

            if task_type == "1":
                equations = [
                    "sin(x) - x/2 = 0",
                    "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06 = 0",
                    "e^x - 3 * x = 0"
                ]
                print("Выберите уравнение:")
                for i, eq in enumerate(equations, 1):
                    print(f"{i}. {eq}")

                eq_choice = get_valid_input("Введите номер уравнения: ", lambda x: validate_choice(x, equations))

                plot_function(eq_choice)

                methods = ["Метод простых итераций", "Метод Хорд", "Метод Cекущих"]
                print("Выберите метод решения:")
                for i, method in enumerate(methods, 1):
                    print(f"{i}. {method}")

                method_choice = get_valid_input("Введите номер метода: ", lambda x: validate_choice(x, methods))

                interval = get_valid_input("Введите интервал (например, -2;2): ", validate_interval)
                precision = get_valid_input("Введите точность (например, 0,001): ", validate_precision)

                data = False, eq_choice, method_choice, interval, precision

            elif task_type == "2":
                systems = [
                    "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0",
                    "0.8x1 + cos(x2) -1 = 0, x1^2 + 0.5x2 - 2 = 0"
                ]
                print("Выберите систему уравнений:")
                for i, system in enumerate(systems, 1):
                    print(f"{i}. {system}")

                system_choice = get_valid_input("Введите номер системы: ", lambda x: validate_choice(x, systems))

                plot_system(system_choice)

                initial_guess = get_valid_input("Введите начальное приближение (например, 0.5,0.5): ",
                                                validate_initial_guess)
                precision = get_valid_input("Введите точность (например, 0.001): ", validate_precision)

                data = True, system_choice, initial_guess, precision

            result = Solver.solve(data)
            process_output(result)

            break
        except Exception as e:
            print(f"Ошибка: {e}. Попробуйте снова.")


def get_valid_input(prompt, validation_func):
    while True:
        try:
            value = input(prompt).strip()
            if not value:
                raise ValueError("Поле не может быть пустым.")
            return validation_func(value)
        except ValueError as ve:
            print(f"Ошибка ввода: {ve}. Попробуйте снова.")


def validate_choice(choice, options):
    try:
        index = int(choice) - 1
        if index not in range(len(options)):
            raise ValueError(f"Введите число от 1 до {len(options)}.")
        return options[index]
    except ValueError:
        raise ValueError(f"Введите число от 1 до {len(options)}.")


def validate_interval(interval):
    try:
        a, b = map(float, interval.replace(",",".").split(";"))
        if a >= b:
            raise ValueError("Левый конец интервала должен быть меньше правого.")
        return interval
    except ValueError:
        raise ValueError("Введите два числа через запятую, например: -2;2.")


def validate_precision(precision):
    try:
        precision_value = float(precision.replace(",","."))
        if precision_value <= 0:
            raise ValueError("Точность должна быть положительным числом.")
        return precision
    except ValueError:
        raise ValueError("Введите положительное число, например: 0.001.")


def validate_initial_guess(initial_guess):
    try:
        guess_values = list(map(float, initial_guess.replace(",",".").split(";")))
        if len(guess_values) != 2:
            raise ValueError("Введите два числа через запятую, например: 0.5,;0.5.")
        return initial_guess
    except ValueError:
        raise ValueError("Введите два числа через запятую, например: 0.5;0.5.")


def plot_function(eq_choice):
    x_vals = np.linspace(-10, 10, 400)

    if eq_choice == "sin(x) - x/2 = 0":
        y_vals = np.sin(x_vals) - x_vals / 2
    elif eq_choice == "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06 = 0":
        y_vals = 2 * x_vals ** 3 + 5.75 * x_vals ** 2 - 7.41 * x_vals - 10.06
    elif eq_choice == "e^x - 3 * x = 0":
        y_vals = np.exp(x_vals) - 3 * x_vals
    else:
        return

    plt.figure()
    plt.plot(x_vals, y_vals, label=eq_choice)
    plt.axhline(0, color='black', linewidth=1)
    plt.axvline(0, color='black', linewidth=1)
    plt.legend()
    plt.grid(True)
    plt.show()


def plot_system(system_choice):
    x_vals = np.linspace(-5, 5, 400)
    y_vals = np.linspace(-5, 5, 400)
    X, Y = np.meshgrid(x_vals, y_vals)
    print(system_choice)
    if system_choice == "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0":
        Z1 = 0.15 * X ** 2 + 0.25 * Y ** 2 + X - 0.4
        Z2 = 0.3 * X ** 2 + 0.2 * X * Y + Y - 0.6
    else:
        Z1 = 0.8 * X + np.cos(Y) -1
        Z2 = X**2 + 0.5 * Y - 2

    plt.figure()
    plt.contour(X, Y, Z1, levels=[0], colors='blue')
    plt.contour(X, Y, Z2, levels=[0], colors='red')
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()