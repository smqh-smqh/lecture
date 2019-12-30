# 交叉熵损失函数
import tensorflow as tf

loss1=tf.losses.categorical_crossentropy([1,0],[0.6,0.4])
loss2=tf.losses.categorical_crossentropy([1,0],[0.8,0.2])
print("loss1:",loss1)
print("loss2:",loss2)