# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import os
num=os.listdir('./num')
print(num)

f = open('./number.txt','w')
for n in num:
    f.write(n.replace(".png"," ")+'\n')
    print(n.replace(".png"," "))
f.close()