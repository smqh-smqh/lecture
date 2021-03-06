# !/user/bin/env python
# coding:utf-8
# Author:wangyx
import tensorflow as tf
import os
import numpy as np

os.environ['TF_CPP_MIN_LOG_LEVEL']='2'

np.set_printoptions(threshold=np.inf)
model_save_path='./checkpoint/mnist.tf'
load_pretain_model=False

mnist=tf.keras.datasets.mnist
(x_train,y_train),(x_test,y_test)=mnist.load_data()
x_train,x_test=x_train/255.0,x_test/255.0

model=tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28,28)),
    tf.keras.layers.Dense(128,activation='relu'),
    tf.keras.layers.Dense(10,activation='softmax')
])

model.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['sparse_categorical_accuracy'])

if load_pretain_model:
    print('-------------load the model---------------')
    model.load_weights(model_save_path)

model.fit(x_train,y_train,epochs=1,validation_freq=2,validation_data=(x_test,y_test))
model.summary()

model.save_weights(model_save_path,save_format='tf')
print(model.trainable_variables)
file=open('./weights.txt','w')
for v in model.trainable_variables:
    file.write(str(v.name)+'\n')
    file.write(str(v.shape) + '\n')
    file.write(str(v.numpy()) + '\n')
file.close()