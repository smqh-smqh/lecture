# !/user/bin/env python
# coding:utf-8
# Author:wangyx

from PIL import Image
import numpy as np
import os
# f = open("./mnist.txt","r")
# contents = f.readlines()
# image_path = "./num/"
# p = ".png"
#
# for content in contents:
#     value = content.split()
#     img_path = image_path + value[1] + p
#     img = Image.open(img_path)
#     img = img.resize((28,28),Image.ANTIALIAS)
#     img = np.array(img.convert('L'))
#     img_arr = img
#     for i in range(28):  # 二值化
#         for j in range(28):
#             if img_arr[i][j] < 230:
#                 img_arr[i][j] = 255
#             else:
#                 img_arr[i][j] = 0
#     im = Image.fromarray(img_arr)
#     im.save(img_path)

np.set_printoptions(threshold=float('inf'))
test_path='./cifar-10/test/'
train_path='./cifar-10/train/'
path='./fashion_pic/'

# kinds=next(os.walk(train_path))[1]
# c=next(os.walk(train_path))[2]
#
# print(kinds)
# print(c)
print(next(os.walk(path)))
def generateds(kinds, path):
    x=[]
    y=[]
    for kind in kinds:
        img_names=next(os.walk(path+str(kind)))[2]
        for img_name in img_names:
            img_path=path+kind+'/'+img_name
            img=Image.open(img_path)
            img=np.array(img)
            x.append(img/255.)
            y.append(kinds.index(kind))
    x = np.array(x)
    y = np.array(y)
    return x,y

# x_train,y_train=generateds(kinds, train_path)

# print(y_train)