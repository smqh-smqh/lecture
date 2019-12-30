# !/user/bin/env python
# coding:utf-8
# Author:wangyx
import tensorflow as tf

mnist=tf.keras.datasets.fashion_mnist
(x_train,y_train),(x_test,y_test)=mnist.load_data()
x_train,x_test=x_train/255.0,x_test/255.0
print(y_train[0],x_train[0])

model=tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28,28)),
    tf.keras.layers.Dense(128,activation='relu'),
    tf.keras.layers.Dense(10,activation='softmax')
])

model.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['sparse_categorical_accuracy'])
model.fit(x_train,y_train,epochs=5,validation_freq=2,validation_data=(x_test,y_test))
model.summary()


