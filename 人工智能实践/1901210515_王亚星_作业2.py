# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

sf = pd.read_csv("SH_600519_high_low.csv")
x = np.arange(0,47)
y1 = sf["high"]
y2 = sf["low"]

plt.subplot(2, 1, 1)
plt.xlabel("day")
plt.ylabel("price")
plt.title("Kweichow Moutai")
plt.legend()
plt.plot(x,y1,label="high")

plt.subplot(2, 1, 2)
plt.xlabel("day")
plt.ylabel("price")
plt.title("Kweichow Moutai")
plt.legend()
plt.plot(x,y2,color="#FF6100",label="low")
plt.show()
