import threading
import solver as solver

def main():
    while True:
     console_input()



def console_input():
    while True:
        try:
            command = get_valid_input("\nВведите команду (solve - решить, exit - выйти): ",
                                      lambda x: x if x in ["solve", "exit"] else ValueError("Введите 'solve' или 'exit'."))

            if command == "exit":
                print("Выход из программы...")
                exit(0)
            elif command == "solve":
                process_console_solution()
        except Exception as e:
            print(f"Ошибка выполнения команды: {e}. Попробуйте снова.")


def process_console_solution():
    while True:
        try:

            equations = [
                "sin(x) + 4",
                "cos(x) - x * sin(x)",
                "x^3 - 2 * x^2 - 2 * x + 2",
                "1/x",
                "1/x^2",
                "4/(3 - x)"
                ]
            print("Выберите уравнение:")
            for i, eq in enumerate(equations, 1):
                print(f"{i}. {eq}")

            eq_choice = get_valid_input("Введите номер уравнения: ", lambda x: validate_choice(x, equations))


            methods = ["Метод левых прямоугольников", "Метод правых прямоугольников", "Метод средних прямоугольников", "Метод трапеций", "Метод Cимпсона"]
            print("Выберите метод решения:")
            for i, method in enumerate(methods, 1):
                print(f"{i}. {method}")

            method_choice = get_valid_input("Введите номер метода: ", lambda x: validate_choice(x, methods))

            interval = get_valid_input("Введите пределы интегрирования (например, -2;2): ", validate_interval)
            precision = get_valid_input("Введите точность (например, 0,001): ", validate_precision)

            data = eq_choice, method_choice, interval, precision
            result = solver.solve(data)
            if result != None:
                output(result)
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


def output(result):
    if(len(result) != 1):
        n , res, iterations = result[0], result[1], result[2]
        print(f"Количество разбиений: {n}\n"
            f"Результат численного интегрирования: {res}\n"
            f"Количество итераций: {iterations} ")
    else:
        print(result)






if __name__ == "__main__":
    main()