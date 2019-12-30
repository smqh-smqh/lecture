# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import tensorflow as tf
from keras import Model
from keras.layers import Dense,Flatten

mnist=tf.keras.datasets.mnist
(x_train,y_train),(x_test,y_test)=mnist.load_data()
x_train,x_test=x_train/255.0,x_test/255.0

class MnistModel(Model):
    def __init__(self):
        super(MnistModel,self).__init__()
        self.flatten = Flatten()
        self.d1 = Dense(128,activation='relu')
        self.d2 = Dense(10,activation='softmax')

    def call(self,x):
        x=self.flatten(x)
        x=self.d1(x)
        y=self.d2(x)
        return y

model=MnistModel()

model.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['sparse_categorical_accuracy'])
model.fit(x_train,y_train,epochs=10,validation_freq=2,validation_data=(x_test,y_test))
model.summary()
