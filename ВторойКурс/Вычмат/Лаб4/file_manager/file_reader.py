import os

def get_input_method():
    while True:
        method = input("Выберите метод ввода данных (1 – файл, 2 – консоль): ").strip()
        if method == "1" or method == "2":
            return method
        print("Ошибка: введите 1 или 2.")

def read_input_from_file():
    while True:
        path = input("Введите путь к файлу: ").strip()
        if not os.path.exists(path):
            print("Ошибка: файл не найден. Попробуйте снова.")
            continue

        try:
            f = open(path, "r", encoding="utf-8")
            lines = f.readlines()
            f.close()
        except:
            print("Ошибка: не удалось открыть файл.")
            continue

        x_vals = []
        y_vals = []
        valid = True

        for idx, line in enumerate(lines, start=1):
            line = line.strip()
            if line == "":
                continue
            parts = line.split()
            if len(parts) != 2:
                print(f"Строка {idx}: должно быть 2 числа, а не {len(parts)}.")
                valid = False
                break
            try:
                x = float(parts[0].replace(",", "."))
                y = float(parts[1].replace(",", "."))
            except:
                print(f"Строка {idx}: неверный формат числа.")
                valid = False
                break
            x_vals.append(x)
            y_vals.append(y)

        if not valid:
            continue

        n = len(x_vals)
        if n < 8 or n > 11:
            print(f"Ошибка: нужно от 8 до 11 точек, в файле {n}.")
            continue

        if len(x_vals) != len(y_vals):
            print("Ошибка: количество X не совпадает с количеством Y.")
            continue

        return x_vals, y_vals