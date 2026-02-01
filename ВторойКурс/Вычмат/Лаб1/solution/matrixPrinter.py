def printMainMatrix(matrix):
    for i in range(len(matrix)):
        print(f"x{i}", end=" " * 12)
    print()
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            print(matrix[i][j], end=" " * 10)
        print()
    print()


def printSupportiveMatrix(matrix):
    for i in range(len(matrix)):
        for j in range(len(matrix[i])):
            print(round(matrix[i][j], 3), end=" " * 10)
        print()
    print()


def printFinalTable(iteration, xk, accuracy):
    print(f"{iteration}".center(36) + " | " + f"{xk}".center(36) + " | " + f"{accuracy}".center(36) + " |")