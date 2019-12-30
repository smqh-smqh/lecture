import tensorflow as tf
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

x = tf.Variable(4)
x.assign_sub(1)
print("x:", x)
