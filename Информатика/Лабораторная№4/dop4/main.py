import time
from dop2.dop2 import main as regex
from dop3.main import hornet
from dop1.dop1 import main as library
from task0.task0 import parser
start_time = time.perf_counter()
for i in range(100):
    parser()
print('own solve:', time.perf_counter() - start_time)

start_time = time.perf_counter()
for i in range(100):
    library()
print('library:', time.perf_counter() - start_time)

start_time=time.perf_counter()
for i in range(100):
    regex()
print('regex:', time.perf_counter() - start_time)

start_time =time.perf_counter()
for i in range(100):
    hornet()
print('formal grammars :', time.perf_counter() - start_time)

