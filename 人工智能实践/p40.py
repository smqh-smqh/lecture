# 对数据集进行可视化，这里只取了四个特征中的前两个做了可视化
from sklearn import datasets
from matplotlib import pyplot as plt

iris = datasets.load_iris()
irisFeature = iris.data
irisTarget = iris.target

for i in range(len(irisTarget)):
    if irisTarget[i] == 0:
        plt.scatter(irisFeature[i, 0], irisFeature[i, 1], color="r")
    elif irisTarget[i] == 1:
        plt.scatter(irisFeature[i, 0], irisFeature[i, 1], color="g")
    else:
        plt.scatter(irisFeature[i, 0], irisFeature[i, 1], color="b")
plt.title("iris")
plt.xlabel("sepal length")
plt.ylabel("sepal width")
plt.show()
