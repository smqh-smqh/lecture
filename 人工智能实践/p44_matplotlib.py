# !/usr/bin/env python
# coding:utf-8
# Author: Caojian

import matplotlib.pyplot as plt
from PIL import Image
img = plt.imread("./a.jpg")
img_tinted = img * [1, 0, 0] #rgb
plt.subplot(1, 2, 1) #1行2列的第一个
plt.imshow(img)
plt.subplot(1, 2, 2) #1行2列的第二个
plt.imshow(img_tinted)
plt.show()
