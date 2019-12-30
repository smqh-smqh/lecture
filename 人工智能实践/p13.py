# tf.where

import tensorflow as tf
a=tf.constant([1,2,3,1,1])
b=tf.constant([0,1,3,4,5])
c=tf.where(tf.equal(a,1))
d=tf.where(tf.greater(a,b),a,b)
print("c：",c)
print("d:",d)