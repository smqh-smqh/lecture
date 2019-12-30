import tensorflow as tf
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息

f = tf.random.uniform([2, 2], minval=0, maxval=1)
print("f:", f)
