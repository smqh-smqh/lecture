# np.mgrid

import numpy as np
# 生成1D数组
a=np.mgrid[-2:2:1]
print("a",a)
# 生成2D矩阵
x, y = np.mgrid[1:3:1, 4:5:0.5]
print("x",x)
print("y",y)