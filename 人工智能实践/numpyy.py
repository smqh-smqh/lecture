# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import numpy as np
x = np.array([[1, 2, 3],[4, 5, 6]],dtype=np.float32)
y=np.zeros((3,4))
z=np.linspace(1,6,6)
w=np.arange(1,7).reshape(2,3)
print(z)
print(np.argsort(w,axis=1))

xx,yy=np.mgrid[-3:3:1,-3:3:1]
print(x.T)
print(np.multiply(x,x))
print(np.dot(x,x.T))
print(np.power(x,2))

x1=np.array([[1,2,3],[11,12,13]])
x2=np.array([[7,8,9],[4,5,6]])
y1=np.r_[x1,x2]
y2=np.c_[x1,x2]

np.save('out.npy',y1)
np.save('out.npy',y2)
y3=np.load('out.npy')
print(y3)
