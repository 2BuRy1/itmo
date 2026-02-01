import os
import re

def get_input_method():
    while True:
        method = input("Выберите метод ввода данных ( 1 – консоль, 2 - функции на выбор): ").strip()
        if method in {"1", "2"}:
            return method
        print("Ошибка: введите 1 или 2")
