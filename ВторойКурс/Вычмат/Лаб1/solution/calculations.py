import solution.matrixPrinter as printer


def make_C_and_D_matrix(matrix):
    C = []
    D = []

    for i in range(len(matrix)):
        a_ii = matrix[i][i]
        C_row = [0 if i == j else (-matrix[i][j] / a_ii) for j in range(len(matrix))]
        C.append(C_row)
        D_row = matrix[i][len(matrix)] / a_ii
        D.append(D_row)

    return C, D


def get_accuracy(x_k, x_k1):
    return max(abs(x_k[i] - x_k1[i]) for i in range(len(x_k)))


def count_norm(M):
    res = 0
    for i in M:
        res = max(res, sum([abs(j) for j in i]))
    return res

def main_calculation(C, D, EPS, n):
    iterations = 0
    x_k = D[:]
    x_k1 = [0] * n

    while iterations == 0 or get_accuracy(x_k, x_k1) >= EPS:
        x_k, x_k1 = x_k1, [0] * n
        for i in range(n):
            x = sum(C[i][j] * x_k[j] for j in range(i, n))
            x += sum(C[i][k] * x_k1[k] for k in range(i))
            x += D[i]
            x_k1[i] = round(x, 6)

        iterations += 1
        printer.printFinalTable(iterations, x_k1, round(get_accuracy(x_k, x_k1), 5))



    return x_k, iterations