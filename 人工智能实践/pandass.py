# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import pandas as pd
df= pd.read_csv("iris.txt",header=None)
x_data=df.iloc[:,0:4].values
epoch=150
flag=0
y_data=[]
for i in range(epoch):
    if df.values[i][4]=="Iris-setosa":
        flag=0
    elif df.values[i][4]=="Iris-versicolor":
        flag = 1
    else:
        flag =2
    y_data.append(flag)
print("y_data\n",y_data,"\n")