import solution.matrixReader as reader
import solution.matrixValidator as validator
import solution.calculations as calculations
import solution.matrixPrinter as printer

def getData():
    letter = input("Если хотите вводить данные через файл, напишите f для считывания матрицы из файла, g для генерации матрицы, иначе введите любой символ: ")

    if letter == "f":
        print("Введите название файла: ")
        matrix, accuracy, n = reader.readMatrixFromTXT(input().strip())
    elif letter == "g":
        matrix, accuracy, n = reader.generateRandomMatrix(int(input("Введите размерность матрицы: ").strip()))
    else:
        matrix, accuracy, n = reader.readMatrixFromCIN()
    return matrix, accuracy, n


def main():
    while(True):
        matrix, accuracy, n = getData()

        matrix = validator.parse_matrix_values(matrix)
        print("Изначальная матрица: \n\n")
        printer.printMainMatrix(matrix)

        if not validator.check_diagonal_dominance(matrix, n):

            print("Матрица не обладает диагональным преобладанием, попробуем это исправить!!\n\n")
            matrix = validator.do_diagonal_dominance(matrix)
            if (matrix == None):
                print("К сожалению, нам не получится привести ее к нужному виду:(")
                exit(1)

        print("Матрица с диагональным преобладанием: ")
        printer.printMainMatrix(matrix)
        C, D = calculations.make_C_and_D_matrix(matrix)

        print("\nНорма матрицы:")
        print(calculations.count_norm(matrix))


        print("\nМатрица C:")
        printer.printSupportiveMatrix(C)

        print("Матрица D:")
        for i in range(len(D)):
            print(D[i], end="")
            print()
        print("Номер итерации".center(37) + "|" + " x^k ".center(38)  + "|" + " max( | x^(k)_i-x^(k+1) | ) ".center(36))
        print("-"*116)
        xk, k = calculations.main_calculation(C, D , accuracy, n)

        print("\nИТОГО\n")
        print(f"\t количество итераций: {k}\n\t ответ с учетом погрешности: {xk}\n\t с округлением: {list([round(i, 3) for i in xk])}")

        if input("Если хотите выйти из программы, напишите exit: ") == "exit":
            exit(0)

if __name__ == "__main__":
    main()
