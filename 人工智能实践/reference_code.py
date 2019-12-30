# -*- coding: UTF-8 -*-
import tensorflow as tf
import os
# from sklearn import datasets
from matplotlib import pyplot as plt
import numpy as np
import pandas as pd

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

# x_data = datasets.load_iris().data
# y_data = datasets.load_iris().target
df = pd.read_csv("iris.txt", header=None)
x_data = df.iloc[:, 0:4].values
epo = 150
flag = 0
y_data = []
for i in range(epo):
    if df.values[i][4] == "Iris-setosa":
        flag = 0
    elif df.values[i][4] == "Iris-versicolor":
        flag = 1
    else:
        flag = 2
    y_data.append(flag)
# print("y_data\n",y_data,"\n")

# 随机打乱数据
np.random.seed(116)
np.random.shuffle(x_data)
np.random.seed(116)
np.random.shuffle(y_data)

y_data = tf.cast(y_data, tf.int32)
x_train = x_data[:-30]
y_train = y_data[:-30]
x_test = x_data[-30:]
y_test = y_data[-30:]

x_train = tf.cast(x_train, tf.float32)
x_test = tf.cast(x_test, tf.float32)

print("x.shape:", x_data.shape)
print("y.shape:", y_data.shape)
print("x.dtype:", x_data.dtype)
print("y.dtype:", y_data.dtype)
print("min of x:", tf.reduce_min(x_data))
print("max of x:", tf.reduce_max(x_data))
print("min of y:", tf.reduce_min(y_data))
print("max of y:", tf.reduce_max(y_data))

# from_tensor_slices函数切分传入的 Tensor 的第一个维度，生成相应的 dataset
train_db = tf.data.Dataset.from_tensor_slices((x_train, y_train)).batch(32)
test_db = tf.data.Dataset.from_tensor_slices((x_test, y_test)).batch(10)
# iter用来生成迭代器
train_iter = iter(train_db)
# next() 返回迭代器的下一个项目
sample = next(train_iter)
print('batch:', sample[0].shape, sample[1].shape)

# seed: 随机数种子，是一个整数，当设置之后，每次生成的随机数都一样
w1 = tf.Variable(tf.random.truncated_normal([4, 32], stddev=0.1, seed=1))
b1 = tf.Variable(tf.random.truncated_normal([32], stddev=0.1, seed=1))
w2 = tf.Variable(tf.random.truncated_normal([32, 32], stddev=0.1, seed=2))
b2 = tf.Variable(tf.random.truncated_normal([32], stddev=0.1, seed=2))
w3 = tf.Variable(tf.random.truncated_normal([32, 3], stddev=0.1, seed=3))
b3 = tf.Variable(tf.random.truncated_normal([3], stddev=0.1, seed=3))

lr = 0.1
train_loss_results = []
epoch = 500
loss_all = 0
for epoch in range(epoch):
    for step, (x_train, y_train) in enumerate(train_db):
        with tf.GradientTape() as tape:
            h1 = tf.matmul(x_train, w1) + b1
            y = tf.matmul(h1, w2) + b2


            y_onehot = tf.one_hot(y_train, depth=3)
            # print('y_onehot:', y_onehot)

            # mse = mean(sum(y-out)^2)
            loss = tf.reduce_mean(tf.square(y_onehot - y))
            loss_all += loss.numpy()

        # compute gradients
        grads = tape.gradient(loss, [w1, b1, w2, b2])
        # w1 = w1 - lr * w1_grad
        w1.assign_sub(lr * grads[0])
        b1.assign_sub(lr * grads[1])
        w2.assign_sub(lr * grads[2])
        b2.assign_sub(lr * grads[3])


        if step % 100 == 0:
            print(epoch, step, 'loss:', float(loss))
    train_loss_results.append(loss_all / 3)
    loss_all = 0

    # test(做测试）
    total_correct, total_number = 0, 0
    for step, (x_train, y_train) in enumerate(test_db):
        h1 = tf.matmul(x_train, w1) + b1
        y = tf.matmul(h1, w2) + b2

        pred = tf.argmax(y, axis=1)

        # 因为pred的dtype为int64，在计算correct时会出错，所以需要将它转化为int32
        pred = tf.cast(pred, dtype=tf.int32)
        correct = tf.cast(tf.equal(pred, y_train), dtype=tf.int32)
        correct = tf.reduce_sum(correct)
        total_correct += int(correct)
        total_number += x_train.shape[0]
    acc = total_correct / total_number
    print("test_acc:", acc)

# 绘制loss曲线
plt.title('Loss Function Curve')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.plot(train_loss_results)
plt.show()
