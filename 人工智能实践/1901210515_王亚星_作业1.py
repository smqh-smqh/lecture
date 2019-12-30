# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

df = pd.read_csv('dot.csv')
x1 = df['x1']
x2 = df['x2']
y_c = df['y_c']
Y_c = [["red" if y else "blue"] for y in y_c]
plt.scatter(x1, x2, color=np.squeeze(Y_c))

pf = pd.read_csv('probs.csv')
pf = pf.values
xx, yy = np.mgrid[-3:3:.01, -3:3:.01]
plt.contour(xx, yy, pf, levels=[.5])
plt.show()
