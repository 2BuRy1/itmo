import os


def get_input_method():
    while True:
        method = input("Выберите метод ввода данных (1 - Файл, 2 - Консоль): ").strip()
        if method in ["1", "2"]:
            return method
        print("Ошибка: Введите 1 или 2.")


def read_input_from_file():
    while True:
        file_path = input("Введите путь к файлу с входными данными: ").strip()
        if not os.path.exists(file_path):
            print("Ошибка: Файл не найден. Попробуйте снова.")
            continue
        try:
            with open(file_path, "r") as file:
                lines = [line.strip() for line in file.readlines() if line.strip()]

            if len(lines) < 4:
                print("Ошибка: В файле должно быть минимум 4 строки с данными. Попробуйте снова.")
                continue

            task_type = lines[0]
            if task_type not in ["1", "2"]:
                print("Ошибка: Первая строка должна быть '1' (уравнение) или '2' (система уравнений).")
                continue

            if task_type == "1":
                return process_equation_input(lines)

            elif task_type == "2":
                return process_system_input(lines)

        except Exception as e:
            print(f"Ошибка при чтении файла: {e} Попробуйте снова.")


def process_equation_input(lines):
    equations = [
        "sin(x) - x/2 = 0",
        "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06 = 0",
        "e^x - 3 * x = 0"
    ]

    methods = ["Метод простых итераций", "Метод Хорд", "Метод Cекущих"]

    try:
        equation_choice = int(lines[1]) - 1
        if equation_choice not in range(len(equations)):
            raise ValueError("Ошибка: Неверный номер уравнения.")

        method_choice = int(lines[2]) - 1
        if method_choice not in range(len(methods)):
            raise ValueError("Ошибка: Неверный номер метода.")

        interval = validate_interval(lines[3])
        precision = validate_precision(lines[4])

        return False, equations[equation_choice], methods[method_choice], interval, precision

    except (ValueError, IndexError) as e:
        print(f"Ошибка обработки уравнения: {e} Попробуйте снова.")
        return read_input_from_file()


def process_system_input(lines):
    systems = [
        "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0",
        "0.8x1 + cos(x2) -1 = 0, x1^2 + 0.5x2 - 2 = 0"
    ]

    try:
        system_choice = int(lines[1]) - 1
        if system_choice not in range(len(systems)):
            raise ValueError("Ошибка: Неверный номер системы уравнений.")

        initial_guess = validate_initial_guess(lines[2])
        precision = validate_precision(lines[3])

        return True, systems[system_choice], initial_guess, precision

    except (ValueError, IndexError) as e:
        print(f"Ошибка обработки системы уравнений: {e} Попробуйте снова.")
        return read_input_from_file()


def validate_interval(interval):

    try:
        a, b = map(float, interval.replace(",",".").split(";"))
        if a >= b:
            raise ValueError("Ошибка в воде интервала. Левый конец интервала должен быть меньше правого.")
        return interval
    except ValueError:
        raise ValueError("Ошибка в воде интервала. Введите два числа через точку с запятой, например: -2;2.")


def validate_precision(precision):
    try:
        precision_value = float(precision.replace(",","."))
        if precision_value <= 0:
            raise ValueError("Ошибка в вводе точности. Точность должна быть положительным числом.")
        return precision
    except ValueError:
        raise ValueError("Ошибка в вводе точности. Введите положительное число, например: 0.001.")


def validate_initial_guess(initial_guess):
    try:
        guess_values = list(map(float, initial_guess.replace(",",".").split(";")))
        if len(guess_values) != 2:
            raise ValueError("Ошибка в воде приближения. Введите два числа через точку с запятой, например: 0.5;0.5.")
        return initial_guess
    except ValueError:
        raise ValueError("Ошибка в воде приближения. Введите два числа через запятую, например: 0.5;0.5.")