import pandas as pd
iris_data=pd.read_csv('D:\iris.txt')#读取数据,根据你自己存放文件的位置，修改引号内的内容
#使用数据说明中的描述自定义表头名称
iris_data .columns=['花萼长度','花萼宽度','花瓣长度','花瓣宽度','类别']
pd.set_option('display.unicode.east_asian_width', True) #调整中文表头与表格内容对齐
print(iris_data.head())#查看前5行数据
