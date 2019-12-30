# !/user/bin/env python
# coding:utf-8
# Author:wangyx

from PIL import Image
import numpy as np
f = open("./mnist.txt","r")
contents = f.readlines()
image_path = "./mnist_data_jpg/"

for content in contents:
    value = content.split()
    img_path = image_path + value[0]
    img = Image.open(img_path)
    img = img.resize((28,28),Image.ANTIALIAS)
    img = np.array(img.convert('L'))
    img_arr = img
    for i in range(28):  # 二值化
        for j in range(28):
            if img_arr[i][j] < 230:
                img_arr[i][j] = 255
            else:
                img_arr[i][j] = 0
    im = Image.fromarray(img_arr)
    im.save(img_path)