import tensorflow as tf
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

a = tf.fill([1, 2], 3.)
print("a:", a)
print("a的平方:", tf.pow(a, 2))
print("a的平方:", tf.square(a))
print("a的开方:", tf.sqrt(a))
