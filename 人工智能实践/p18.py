# softmax函数
import tensorflow as tf

a = tf.constant([3.0,1.0,0.3])
a_ = tf.nn.softmax(a)
print("After softmax,a is:",a_)
print("The sum of a_:",tf.reduce_sum(a_))