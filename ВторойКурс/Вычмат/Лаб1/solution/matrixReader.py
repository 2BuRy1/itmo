from os.path import exists
import random


def readMatrixFromCIN():
    while True:
        try:
            size = int(input("Введите размерность матрицы ≤20: "))
            if size > 20 or size <= 0:
                print("Размерность должна быть от 1 до 20.")
                continue

            accuracy = float(input("Введите точность: ").replace(',', '.'))
            if accuracy <= 0:
                print("Точность должна быть больше 0.")
                continue

            print("Введите матрицу: ")
            matrix = [input().strip().split(" ") for _ in range(size)]

            if len(matrix) != size or any(len(row) != size + 1 for row in matrix):
                print("Вы ввели неверное количество элементов, попробуйте снова: ")
                continue

            matrix = [[float(el.replace(',', '.')) for el in row] for row in matrix]

            return matrix, accuracy, size

        except (ValueError, IndexError):
            print("Неверный ввод. Попробуйте снова.")


def readMatrixFromTXT(FilePath):
    while True:
        try:
            if not exists(FilePath):
                print("Такого файла нет :(")
                FilePath = input("Введите путь к файлу: ")
                continue

            with open(FilePath) as f:
                size = int(f.readline().strip())
                if size > 20 or size <= 0:
                    print("Размерность должна быть от 1 до 20.")
                    continue

                accuracy = float(f.readline().strip().replace(',', '.'))
                if accuracy <= 0:
                    print("Точность должна быть больше 0, измените содержимое файла и перезапустите программу.")
                    exit(1)

                matrix = [line.strip().split(" ") for line in f.readlines()]

                if len(matrix) != size or any(len(row) != size + 1 for row in matrix):
                    print("Вы ввели неверное количество элементов, попробуйте снова: ")
                    continue

                matrix = [[float(el.replace(',', '.')) for el in row] for row in matrix]

                return matrix, accuracy, size

        except (ValueError, IndexError):
            print("Ошибка при чтении файла. Попробуйте снова.")


def generateRandomMatrix(size, accuracy_range=(0.0001, 1.0), value_range=(-100, 100)):
    while True:
        try:
            if size > 20 or size <= 0:
                print("Размерность должна быть от 1 до 20.")
                size = int(input("Введите размерность: "))
                continue

            accuracy = round(random.uniform(*accuracy_range), 6)
            matrix = [[str(random.randint(*value_range)).replace('.', ',') for _ in range(size + 1)] for _ in range(size)]

            return matrix, accuracy, size

        except ValueError:
            print("Ошибка при генерации матрицы. Попробуйте снова.")