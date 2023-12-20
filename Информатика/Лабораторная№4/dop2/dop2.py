import time
import re
def main():
    r = open('schedule.xml', 'r', encoding="utf-8")
    w = open('output.yaml', 'w', encoding="utf-8")
    file = r.read().replace("><", ">\n<").split("\n")
    file.pop(0)
    f=0
    file1 =[]
    for v in file:
        if v.count(" ") != len(v):
            string =v
        while string[0] == " ":
            string = string.replace(' ', "", 1)
        file1.append('  '* f + string)
        f += string.count("<") - 2*string.count("</")

    u = "\n".join(file1)
    while " >" in u:
        u =re.sub(r' >', '>', u)
        if "< " in u:
            u = re.sub(r'< ', '<', u)

    l = re.sub(r'\n\s*\n', "\n",re.sub(r'>',": " ,(re.sub(r'/\w+>'," ", u.replace("<", "")))))
    w.write(l)
    r.close()
    w.close()
