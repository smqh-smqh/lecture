# !/usr/bin/env python
# coding:utf-8
# Author: Caojian
from PIL import Image  # 引入PIL
import numpy as np

img = Image.open('./num/0.png')  # 打开0.png
img = img.resize((28, 28), Image.ANTIALIAS)
img = np.array(img.convert('L'))
img_arr = img

for i in range(28):  # 二值化
    for j in range(28):
        if img_arr[i][j] > 230:
            img_arr[i][j] = 255
        else:
            img_arr[i][j] = 0
im = Image.fromarray(img_arr)  # 将array转成Image
im.save('./two.jpg')  # 保存图片
