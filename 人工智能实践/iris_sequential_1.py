# !/user/bin/env python
# coding:utf-8
# Author:wangyx
# !/user/bin/env python
# coding:utf-8
# Author:wangyx
import tensorflow as tf
import numpy as np
from sklearn import datasets
from keras import Model
from tensorflow.keras.layers import Dense,Flatten
import os

x_train=datasets.load_iris().data
y_train=datasets.load_iris().target

np.random.seed(116)
np.random.shuffle(x_train)
np.random.seed(116)
np.random.shuffle(y_train)

#model=tf.keras.models.Sequential([tf.keras.layers.Dense(3,activation='sigmoid')])
class IrisModel(tf.keras.Model):
    def __init__(self):
        super(IrisModel,self).__init__()
        self.dl=tf.keras.layers.Dense(3,activation='sigmoid')

    def call(self,x):
        y=self.dl(x)
        return y

model=IrisModel()
model.compile(optimizer=tf.keras.optimizers.SGD(lr=0.1),loss='sparse_categorical_crossentropy',metrics=['sparse_categorical_accuracy'])
model.fit(x_train,y_train,epochs=500,validation_freq=20,validation_split=0.2)
model.summary()

