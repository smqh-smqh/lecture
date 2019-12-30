import tensorflow as tf
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

classes = 3
labels1 = tf.constant([1, 0, 2])  # 输入的元素值最小为0，最大为2
output = tf.one_hot(labels1, depth=classes)
print("result of labels1:", output)
print("\n")

labels2 = tf.constant([1, 4, 2])  # 输入的元素值4超出depth-1
output = tf.one_hot(labels2, depth=classes)
print("result of labels2:", output)
