import time
def parser():
    r = open('schedule.xml', 'r', encoding="utf-8")
    w = open('output.yaml', 'w', encoding="utf-8")
    k = 0
    x = r.read().split('\n')
    for i in range(len(x)):
        a = x[i].strip().replace('<', '>').split('>')[1:-1]
        if len(a) > 2:
            a = a[:-1]
        if len(a) == 1:
            if a[0][0] == '/':
                k -= 1
            else:
                w.write('  ' * k + a[0] + ":" + '\n')
                k += 1
        else:
            if len(a)==0:
                a = ""
            else:
                w.write('  ' * k + a[0]+ ': ' +a[1]  + '\n')
    r.close()
    w.close()


