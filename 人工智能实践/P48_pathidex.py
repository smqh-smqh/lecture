# !/usr/bin/env python
# coding:utf-8
# Author: Caojian
f = open("./mnist.txt", "r")
image_path = "./mnist_data_jpg/"
contents = f.readlines()
print(contents)

for content in contents:
    value = content.split()
    img_path = image_path + value[0]
    print(img_path)
