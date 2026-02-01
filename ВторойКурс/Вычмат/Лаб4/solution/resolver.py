import math


def _gauss(a):
    n = len(a)
    for k in range(n):
        i = max(range(k, n), key=lambda r: abs(a[r][k]))

        a[k], a[i] = a[i], a[k]
        piv = a[k][k]
        for j in range(k, n + 1):
            a[k][j] /= piv
        for r in range(k + 1, n):
            f = a[r][k]
            for j in range(k, n + 1):
                a[r][j] -= f * a[k][j]

    x = [0.0] * n
    for i in range(n - 1, -1, -1):
        x[i] = a[i][n] - sum(a[i][j] * x[j] for j in range(i + 1, n))
    return x


def _poly_fit(x, y, deg):
    m = deg + 1
    S = [sum(xi ** k for xi in x) for k in range(2 * deg + 1)]
    B = [sum(yi * (xi ** k) for xi, yi in zip(x, y)) for k in range(m)]
    A = [[S[i + j] for j in range(m)] + [B[i]] for i in range(m)]
    return _gauss(A)


def make_sums(x_values, y_values):
    sx = sum(x_values)
    sy = sum(y_values)
    sxx = sum(x * x for x in x_values)
    sxy = sum(x * y for x, y in zip(x_values, y_values))
    sxxx = sum(x ** 3 for x in x_values)
    sxxxx = sum(x ** 4 for x in x_values)
    sxxxxx = sum(x ** 5 for x in x_values)
    sxxxxxx = sum(x ** 6 for x in x_values)
    sxxy = sum(x * x * y for x, y in zip(x_values, y_values))
    sxxxy = sum(x * x * x * y for x, y in zip(x_values, y_values))
    return (sx, sy, sxx, sxy, sxxx, sxxxx, sxxxxx, sxxxxxx, sxxy, sxxxy)


def calculate_least_squares_metrics(x_values, y_values, phi_array):
    residuals = [phi - y for phi, y in zip(phi_array, y_values)]
    mse = sum(e ** 2 for e in residuals) / len(x_values)
    rmse = math.sqrt(mse)
    phi_mean = sum(phi_array) / len(phi_array)
    ss_tot = sum((y - phi_mean) ** 2 for y in y_values)
    ss_res = sum(e ** 2 for e in residuals)
    r_squared = 1 - (ss_res / ss_tot if ss_tot != 0 else 0)
    return residuals, mse, rmse, r_squared


def pearson_correlation(x_values, y_values):
    n = len(x_values)
    mean_x = sum(x_values) / n
    mean_y = sum(y_values) / n
    num = sum((x - mean_x) * (y - mean_y) for x, y in zip(x_values, y_values))
    den = math.sqrt(sum((x - mean_x) ** 2 for x in x_values) *
                    sum((y - mean_y) ** 2 for y in y_values))
    return num / den if den != 0 else 0


def interpret_r_squared(r2):
    if r2 >= 0.95:
        return "Высокая точность аппроксимации"
    if 0.95 >r2 >= 0.75:
        return "Удовлетворительная точность аппроксимации"
    if 0.75 > r2 >= 0.5:
        return "Слабая точность аппроксимации"
    return "Точность слишком мала, модель нужно поменять"


def interpret_pearson(r):
    r = abs(r)

    if 0.9 < r < 1:
        return "Связь весьма высокая"
    if 0.7<r<=0.9:
        return "Связь высокая"
    if 0.5<r<=0.7:
        return "Связь заметная"
    if 0.3 < r <= 0.5:
        return "Умеренная связь"
    if r >= 0.3:
        return "Слабая связь"
    return "Почти отсутствует корреляция"



def calculate_linear(x, y):
    n = len(x)
    sx, sy, sxx, sxy, *_ = make_sums(x, y)
    det = n * sxx - sx * sx
    if det == 0:
        return None
    a = (sy * sxx - sx * sxy) / det
    b = (n * sxy - sx * sy) / det
    phi = [a + b * xi for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    r_p = pearson_correlation(x, y)
    return [a, b], f"{a:.4f} + {b:.4f} * x", phi, eps, rmse, r2, \
           interpret_r_squared(r2), r_p, interpret_pearson(r_p)


def calculate_second(x, y):
    coef = _poly_fit(x, y, 2)
    if coef is None:
        return None
    a0, a1, a2 = coef
    phi = [a0 + a1 * xi + a2 * xi ** 2 for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    eq = f"{a0:.4f} + {a1:.4f} * x + {a2:.4f} * x^2"
    return coef, eq, phi, eps, rmse, r2, interpret_r_squared(r2)


def calculate_third(x, y):
    coef = _poly_fit(x, y, 3)
    if coef is None:
        return None
    a0, a1, a2, a3 = coef
    phi = [a0 + a1 * xi + a2 * xi ** 2 + a3 * xi ** 3 for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    eq = f"{a0:.4f} + {a1:.4f} * x + {a2:.4f} * x^2 + {a3:.4f} * x^3"
    return coef, eq, phi, eps, rmse, r2, interpret_r_squared(r2)


def calculate_exp(x, y):
    if any(yi <= 0 for yi in y):
        return None
    ln_y = [math.log(yi) for yi in y]
    res = calculate_linear(x, ln_y)
    if res is None:
        return None
    ln_a, b = res[0]
    a = math.exp(ln_a)
    phi = [a * math.exp(b * xi) for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    eq = f"{a:.4f} * e^({b:.4f} * x)"
    return [a, b], eq, phi, eps, rmse, r2, interpret_r_squared(r2)


def calculate_log(x, y):
    if any(xi <= 0 for xi in x):
        return None
    ln_x = [math.log(xi) for xi in x]
    res = calculate_linear(ln_x, y)
    if res is None:
        return None
    a, b = res[0]
    phi = [a + b * math.log(xi) for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    eq = f"{a:.4f} + {b:.4f} * ln(x)"
    return [a, b], eq, phi, eps, rmse, r2, interpret_r_squared(r2)


def calculate_power(x, y):
    if any(xi <= 0 for xi in x) or any(yi <= 0 for yi in y):
        return None
    ln_x = [math.log(xi) for xi in x]
    ln_y = [math.log(yi) for yi in y]
    res = calculate_linear(ln_x, ln_y)
    if res is None:
        return None
    ln_a, b = res[0]
    a = math.exp(ln_a)
    phi = [a * (xi ** b) for xi in x]
    eps, _, rmse, r2 = calculate_least_squares_metrics(x, y, phi)
    eq = f"{a:.4f} * x^{b:.4f}"
    return [a, b], eq, phi, eps, rmse, r2, interpret_r_squared(r2)



def calculate_interpolations(x_values, y_values):

    methods = {
        "Линейная":         calculate_linear,
        "Квадратичная":     calculate_second,
        "Кубическая":       calculate_third,
        "Экспоненциальная": calculate_exp,
        "Логарифмическая":  calculate_log,
        "Степенная":        calculate_power
    }

    res = {}
    for name, fn in methods.items():
        try:
            out = fn(x_values, y_values)
            if out is not None:
                res[name] = out
        except Exception:
            continue
    return res