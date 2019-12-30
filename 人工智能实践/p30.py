import tensorflow as tf
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

a = tf.ones([2, 2, 3])
b = tf.fill([3, 2], 3.)
print("a:", a)
print("b:", b)
print("a*b:", tf.matmul(a, b))
