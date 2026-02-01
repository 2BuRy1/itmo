import math


def newton_divided_interp(x, div_diff_table, arg):
    n = len(x)
    result = div_diff_table[0][0]
    product = 1
    for i in range(1, n):
        product *= (arg - x[i - 1])
        result += div_diff_table[i][0] * product
    return result
# y0 + f(x0, x1) * (x - x0) + f(x0, x1, x2) * (x - x0) * (x - x1) ...



def build_central_diff_table(y):
    n = len(y)
    table = [y.copy()]
    for i in range(1, n):
        row = []
        for j in range(n - i):
            row.append(table[i - 1][j + 1] - table[i - 1][j])
        table.append(row)
    return table


def divided_differences(x, y):
    n = len(x)
    table = [y.copy()]
    for i in range(1, n):
        row = []
        for j in range(n - i):
            numerator = table[i - 1][j + 1] - table[i - 1][j]
            denominator = x[j + i] - x[j]
            row.append(numerator / denominator)
        table.append(row)
    return table

def solve(x, y, arg):
    if len(x) != len(y):
        raise ValueError("Количество x и y значений должно совпадать")
    if len(x) < 2:
        raise ValueError("Нужно как минимум 2 точки для интерполяции")
    if len(set(x)) != len(x):
        raise ValueError("Значения x должны быть уникальными (нет повторов)")

    points = sorted(zip(x, y), key=lambda p: p[0])
    x_sorted = [p[0] for p in points]
    y_sorted = [p[1] for p in points]

    def finite_differences(y):
        n = len(y)
        table = [y.copy()]
        for i in range(1, n):
            row = []
            for j in range(n - i):
                row.append(table[i - 1][j + 1] - table[i - 1][j])
            table.append(row)
        return table




    diff_table = finite_differences(y_sorted)

    def gauss_interpolation(x, y, arg, formula_type='first'):
        n = len(x)
        h = x[1] - x[0]

        fin_diff = [y.copy()]
        for i in range(1, n):
            fin_diff.append([fin_diff[i - 1][j + 1] - fin_diff[i - 1][j]
                             for j in range(n - i)])

        if formula_type == 'first':
            center_idx = n // 2
            t = (arg - x[center_idx]) / h
            result = y[center_idx]
            product = 1

            for k in range(1, n):
                # idx смещается влево и вправо чередующимися шагами
                if k % 2 == 1:
                    idx = center_idx - (k // 2 + 1)
                else:
                    idx = center_idx - (k // 2)

                if idx < 0 or idx >= len(fin_diff[k]):
                    break

                product *= (t + (-1) ** (k % 2) * (k // 2)) / k
                result += product * fin_diff[k][idx]

        elif formula_type == 'second':
            center_idx = n // 2 - 1
            t = (arg - x[center_idx]) / h
            result = y[center_idx]
            product = 1

            for k in range(1, n):
                if k % 2 == 1:
                    idx = center_idx - (k // 2)
                else:
                    idx = center_idx - (k // 2)

                if idx < 0 or idx >= len(fin_diff[k]):
                    break

                product *= (t - (-1) ** (k % 2) * (k // 2)) / k
                result += product * fin_diff[k][idx]

        else:
            raise ValueError("formula_type must be 'first' or 'second'")

        return result

    # arg < x[center> ? second ->

    def lagrange_interpolation(x, y, arg):
        n = len(x)
        result = 0.0
        for i in range(n):
            term = y[i]
            for j in range(n):
                if j != i:
                    term *= (arg - x[j]) / (x[i] - x[j])
            result += term
        return result



    lagrange_val = lagrange_interpolation(x_sorted, y_sorted, arg)
    new_table = divided_differences(x_sorted, y_sorted)
    newton_val = newton_divided_interp(x_sorted, new_table, arg)
    gauss_val = gauss_interpolation(x_sorted, y_sorted, arg)

    def format_diff_table(diff_table):
        max_len = max(len(str(val)) for row in diff_table for val in row)
        formatted = []
        for i, row in enumerate(diff_table):
            formatted_row = [f"{'Δ' * i}y:"] + [f"{val:>{max_len}.4f}" for val in row]
            formatted.append(" ".join(formatted_row))
        return "\n".join(formatted)

    central_diff_table = build_central_diff_table(y_sorted)

    return {
        "Таблица конечных разностей": format_diff_table(diff_table),
        "Интерполяция Ньютона": newton_val,
        "Интерполяция Гаусса": gauss_val,
        "Интерполяция Лагранжа": lagrange_val,
        "diff_table": diff_table,
        "central_diff_table": central_diff_table,
        "x_sorted": x_sorted,
        "y_sorted": y_sorted,
        "arg": arg
    }






