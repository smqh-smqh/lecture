# !/usr/bin/env python
# coding:utf-8
# Author: Caojian

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv("SH_600519.csv")
x = np.arange(0, 47)
y1 = df["open"]
y2 = df["close"]
print("x:\n", x, "\ny1:\n", y1, "\ny2:\n", y2)
plt.xlabel("day")
plt.ylabel("price")
plt.title("Kweichow Moutai")
plt.plot(x, y1, label="open")
plt.plot(x, y2, label="close")
plt.legend()
plt.show()
