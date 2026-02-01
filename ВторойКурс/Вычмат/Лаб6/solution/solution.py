
def make_grid(x0, xn, h):
    n = int(round((xn - x0) / h)) + 1
    return [x0 + i * h for i in range(n)]


def improved_euler(f, xs, y0):
    ys = [y0]
    h = xs[1] - xs[0]
    for i in range(len(xs) - 1):
        y_pred = f(xs[i], ys[i])
        y_corr = f(xs[i] + h, ys[i] + h * y_pred)
        ys.append(ys[i] + 0.5 * h * (y_pred + y_corr))
    return ys


def runge_kutta4(f, xs, y0):
    ys = [y0]
    h = xs[1] - xs[0]
    for i in range(len(xs) - 1):
        k1 = h * f(xs[i], ys[i])
        k2 = h * f(xs[i] + h / 2, ys[i] + k1 / 2)
        k3 = h * f(xs[i] + h / 2, ys[i] + k2 / 2)
        k4 = h * f(xs[i] + h, ys[i] + k3)
        ys.append(ys[i] + (k1 + 2 * k2 + 2 * k3 + k4) / 6)
    return ys


def    milne(f, xs, y0, eps):
    n = len(xs)
    h = xs[1] - xs[0]
    y = [y0]

    for i in range(1, 4):
        k1 = h * f(xs[i - 1], y[i - 1])
        k2 = h * f(xs[i - 1] + h / 2, y[i - 1] + k1 / 2)
        k3 = h * f(xs[i - 1] + h / 2, y[i - 1] + k2 / 2)
        k4 = h * f(xs[i - 1] + h, y[i - 1] + k3)
        y.append(y[i - 1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6)

    for i in range(4, n):
        yp = y[i - 4] + (4 * h / 3) * (2 * f(xs[i - 3], y[i - 3])
                                      - f(xs[i - 2], y[i - 2])
                                      + 2 * f(xs[i - 1], y[i - 1]))
        y_next = yp
        while True:
            yc = y[i - 2] + (h / 3) * (f(xs[i - 2], y[i - 2])
                                      + 4 * f(xs[i - 1], y[i - 1])
                                      + f(xs[i], y_next))
            if abs(yc - y_next) < eps:
                y_next = yc
                break
            y_next = yc
        y.append(y_next)

    return y


def runge_error(y_h, y_half, p):
    m = min(len(y_h), (len(y_half) + 1) // 2)
    return [abs(y_h[i] - y_half[2 * i]) / (2 ** p - 1) for i in range(m)]


def improved_euler_error(f, xs, y0):
    h = xs[1] - xs[0]
    xs2 = make_grid(xs[0], xs[-1], h / 2)
    return runge_error(improved_euler(f, xs, y0), improved_euler(f, xs2, y0), 1)


def improved_euler_until_eps(f, x0, xn, y0, h, eps, max_iter=20):
    for _ in range(max_iter):
        xs = make_grid(x0, xn, h)
        xs2 = make_grid(x0, xn, h / 2)
        err = runge_error(improved_euler(f, xs, y0), improved_euler(f, xs2, y0), 1)
        if max(err) <= eps or h < 1e-10:
            return xs, improved_euler(f, xs, y0), err
        h /= 2
    return xs, improved_euler(f, xs, y0), err


def rk4_until_eps(f, x0, xn, y0, h, eps, max_iter=20):
    for _ in range(max_iter):
        xs = make_grid(x0, xn, h)
        xs2 = make_grid(x0, xn, h / 2)
        err = runge_error(runge_kutta4(f, xs, y0), runge_kutta4(f, xs2, y0), 4)
        if max(err) <= eps or h < 1e-10:
            return xs, runge_kutta4(f, xs, y0), err
        h /= 2
    return xs, runge_kutta4(f, xs, y0), err


def milne_until_eps(f, x0, xn, y0, h, y_exact, eps, max_iter=20):
    for _ in range(max_iter):
        xs = make_grid(x0, xn, h)
        y_m = milne(f, xs, y0, eps)
        err = [abs(ye - ym) for ye, ym in zip(y_exact, y_m)]
        if max(err) <= eps or h < 1e-10:
            return xs, y_m, err
        h /= 2
    return xs, y_m, err