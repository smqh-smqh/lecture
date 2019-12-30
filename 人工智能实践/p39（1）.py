# 打印数据集的前十行，利用Pandas可视化数据集
from sklearn import datasets
from pandas import DataFrame
import pandas as pd

x_data = datasets.load_iris().data
y_data = datasets.load_iris().target
x= DataFrame(x_data,columns=['花萼长度','花萼宽度','花瓣长度','花瓣宽度'])
x['类别']=y_data  # 合并特征值与标签值
pd.set_option('display.unicode.east_asian_width', True) #调整表头与表格内容对齐
print(x.head(10))