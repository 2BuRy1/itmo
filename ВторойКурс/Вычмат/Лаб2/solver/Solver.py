import math
MAX_ITERATIONS = 20000

def solve(data):
    if data[0]:
        return solve_system(data)
    else:
        return solve_equation(data)


def parse_interval(interval_str):
    try:
        a, b = map(float, interval_str.replace(",", ".").split(';'))
        return a, b
    except ValueError:
        raise ValueError("Некорректный формат интервала. Введите два числа через точку с запятой.")


def check_root_existence(f, a, b):
    if f(a) * f(b) > 0:
        return "На данном интервале нет корня или несколько. Выберите другой интервал."
    return None


def solve_by_hord(data):
    equation, interval_str, precision_str = data[1], data[3], data[4]
    a, b = parse_interval(interval_str)
    eps = float(precision_str.replace(",", "."))

    equation = equation.split("=")[0].strip().replace("^", "**")

    f = lambda x: eval(equation,
                       {"x": x, "sin": math.sin, "cos": math.cos, "exp": math.exp, "log": math.log, "e": math.e})
    root_check = check_root_existence(f, a, b)
    if root_check:
        return root_check

    iterations = 0
    while abs(b - a) > eps:
        x_i = a - (b - a) / (f(b) - f(a)) * f(a)
        if f(x_i) == 0:
            return f"Решение: {x_i}, Значение функции: {f(x_i)}, Количество итераций: {iterations}"
        elif f(a) * f(x_i) < 0:
            b = x_i
        else:
            a = x_i
        iterations += 1
    return f"Решение: {x_i}, Значение функции: {f(x_i)}, Количество итераций: {iterations}"


def solve_by_sec(data):
    equation, interval_str, precision_str = data[1], data[3], data[4]
    a, b = parse_interval(interval_str)
    eps = float(precision_str.replace(",", "."))

    equation = equation.split("=")[0].strip().replace("^", "**")

    f = lambda x: eval(equation,
                       {"x": x, "sin": math.sin, "cos": math.cos, "exp": math.exp, "log": math.log, "e": math.e})
    root_check = check_root_existence(f, a, b)
    if root_check:
        return root_check

    x0, x1 = a, b
    iterations = 0
    while abs(x1 - x0) > eps:
        x_new = x1 - (x1 - x0) / (f(x1) - f(x0)) * f(x1)
        x0, x1 = x1, x_new
        iterations += 1
    return f"Решение: {x1}, Значение функции: {f(x1)}, Количество итераций: {iterations}"


def df_dx_sin(x):
    return math.cos(x) - 0.5


def df_dx_poly(x):
    return 6 * x ** 2 + 11.5 * x - 7.41


def df_dx_exp(x):
    return math.exp(x) - 3


def solve_by_iterations(data):
    equation, interval_str, precision_str = data[1], data[3], data[4]
    a, b = parse_interval(interval_str)
    eps = float(precision_str.replace(",", "."))

    equation = equation.split("=")[0].strip().replace("^", "**")

    f = lambda x: eval(equation,
                       {"x": x, "sin": math.sin, "cos": math.cos, "exp": math.exp, "log": math.log, "e": math.e})



    if equation == "sin(x) - x/2":
        df = df_dx_sin
    elif equation == "2 * x**3 + 5.75 * x ** 2 - 7.41 * x - 10.06":
        df = df_dx_poly
    elif equation == "e**x - 3 * x":
        df = df_dx_exp
    else:
        return "Ошибка: Неизвестное уравнение!"

    maxEl = max(df(a), df(b))
    lamda = 1 / max(abs(df(a)), abs(df(b)))
    if maxEl > 0:
        lamda = -1 / max(abs(df(a)), abs(df(b)))

    phi = lambda x: x + lamda * f(x)
    dphi = lambda x: 1 + lamda * df(x)

    root_check = check_root_existence(f, a, b)
    if root_check:
        return root_check

    q = max(abs(dphi(a)), abs(dphi(b)))
    if q >= 1:
        return "Метод простой итерации не сходится."

    x_i = a
    iterations = 0
    while abs(f(x_i)) > eps:
        x_i = phi(x_i)
        iterations += 1

    return f"Решение: {x_i}, Значение функции: {f(x_i)}, Количество итераций: {iterations}"


def solve_equation(data):
    if data[2] == "Метод Хорд":
        return solve_by_hord(data)
    elif data[2] == "Метод Cекущих":
        return solve_by_sec(data)
    elif data[2] == "Метод простых итераций":
        return solve_by_iterations(data)
    else:
        return "Неизвестный метод."

def df1_dx1(x1, x2):
    return -0.3 * x1

def df1_dx2(x1, x2):
    return -0.5 * x2

def dg1_dx1(x1, x2):
    return -0.6 * x1 - 0.2 * x2

def dg1_dx2(x1, x2):
    return -0.2 * x1


def df2_dx1(x1, x2):
    return 0

def df2_dx2(x1, x2):
    return math.cos(x2)/2

def dg2_dx1(x1, x2):
    return math.sin(x1)/3

def dg2_dx2(x1, x2):
    return 0

def compute_jacobian(x1_val, x2_val, system_choice):

    if system_choice == "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0":
        return [
        [df1_dx1(x1_val, x2_val), df1_dx2(x1_val, x2_val)],
        [dg1_dx1(x1_val, x2_val), dg1_dx2(x1_val, x2_val)]
    ]


    elif system_choice == "2x1 - sin(x2) - 1 = 0, 3x2 - cos(x1) - 2 = 0":
        return [
            [df2_dx1(x1_val, x2_val), df2_dx2(x1_val, x2_val)],
            [dg2_dx1(x1_val, x2_val), dg2_dx2(x1_val, x2_val)]
        ]


def check_convergence(phi_functions, initial_guess, system_choice):
    x1_val, x2_val = initial_guess
    jacobian_values = compute_jacobian(x1_val, x2_val, system_choice)

    max_row_sum = max(sum(abs(jacobian_values[i][j]) for j in range(2)) for i in range(2))
    return max_row_sum < 1


def system1_phi1(x1, x2):
    return 0.4 - 0.15 * x1 ** 2 - 0.25 * x2 ** 2


def system1_phi2(x1, x2):
    return 0.6 - 0.3 * x1 ** 2 - 0.2 * x1 * x2


def system2_phi1(x1, x2):
    return (math.sin(x2) + 1) / 2


def system2_phi2(x1, x2):
    return (math.cos(x1) + 2)/3


def solve_system(data):
    system_choice = data[1]
    initial_guess = parse_interval(data[2])
    epsilon = data[3]

    if(epsilon < 0):
        return "Введенная точность меньше нуля!!"

    if system_choice == "0.15x1^2 + 0.25x2^2 + x1 - 0.4 = 0, 0.3x1^2 + 0.2x1x2 + x2 - 0.6 = 0":
        phi_functions = [system1_phi1, system1_phi2]
    elif system_choice == "2x1 - sin(x2) - 1 = 0, 3x2 - cos(x1) - 2 = 0":
        phi_functions = [system2_phi1, system2_phi2]
    else:
        return "Неизвестная система уравнений."
    if not check_convergence(phi_functions, initial_guess, system_choice):
        return "Метод простой итерации не сходится."

    x_k = initial_guess[:]
    iterations = 0

    while True:
        x_k1 = [phi(x_k[0], x_k[1]) for phi in phi_functions]
        error = [abs(x_k1[i] - x_k[i]) for i in range(2)]
        iterations += 1

        if max(error) <= epsilon:
            break

        if iterations >= MAX_ITERATIONS:
            return "Превышено количество ожидания. Метод будет сходиться очень долго, либо не сойдется вообще."

        x_k = x_k1[:]

    return {"Решение": x_k1, "Число итераций": iterations, "Погрешность": error}