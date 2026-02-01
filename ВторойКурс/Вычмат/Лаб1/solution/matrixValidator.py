def check_diagonal_dominance(matrix, n):
    for i in range(n):
        sum_non_diag = sum(abs(matrix[i][j]) for j in range(n) if j != i)
        if abs(matrix[i][i]) < sum_non_diag:
            return False
    return True


def parse_matrix_values(matrix, precision=3):
    if matrix is None:
        print("Ошибка: матрица не была загружена.")
        exit(1)

    try:
        float_matrix = [[round(float(element), precision) for element in row] for row in matrix]
    except ValueError:
        print("Данные в матрице невалидны")
        exit(1)

    return float_matrix


def swap_rows(matrix, row1, row2):
    matrix[row1], matrix[row2] = matrix[row2], matrix[row1]


def count_norm(M):
    res = 0
    for i in M:
        res = max(res, sum([abs(j) for j in i]))
    return res

def do_diagonal_dominance(matrix):
    for i in range(len(matrix)):
        max_row = i
        for j in range(i + 1, len(matrix)):
            if abs(matrix[j][i]) > abs(matrix[max_row][i]):
                max_row = j
        if max_row != i:
            swap_rows(matrix, max_row, i)


    if not check_diagonal_dominance(matrix, len(matrix)):
            print("Матрицу нельзя привести к диагонально доминирующему виду.")
            return None

    return matrix
