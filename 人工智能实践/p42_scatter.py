# !/usr/bin/env python
# coding:utf-8
# Author: Caojian

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
df = pd.read_csv('dot.csv')
x1=df['x1']
x2=df['x2']
y_c=df['y_c']
print("x1:\n", x1, "\nx2:\n", x2, "\ny_c:\n", y_c)
Y_c = [['red' if y else 'blue'] for y in y_c]
print("Y_c:\n", Y_c, "\nnp.squeeze(Y_c):\n", np.squeeze(Y_c))
#用plt.scatter画出数据集X各行中第1列元素和第2列元素的点即各行的（x1，x2）
plt.scatter(x1, x2, color=np.squeeze(Y_c))
plt.show()
