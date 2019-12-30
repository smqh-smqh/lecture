# !/user/bin/env python
# coding:utf-8
# Author:wangyx

def hi_name(yourname):
    print("hello %s" % yourname)


hi_name("yaxing")


def add(a, b):
    return a + b


c = add(5, 6)
print(c)

import turtle

t = turtle.Pen()
t.forward(100)
t.backward(10)
t.left(90)
t.right(10)
t.forward(200)
t.reset()
