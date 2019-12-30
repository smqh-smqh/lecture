# !/user/bin/env python
# coding:utf-8
# Author:wangyx
import tensorflow as tf
import numpy as np
from sklearn import datasets
import os

x_train=datasets.load_iris().data
y_train=datasets.load_iris().target

# np.random.seed(116)
# np.random.shuffle(x_train)
# np.random.seed(116)
# np.random.shuffle(y_train)

model=tf.keras.models.Sequential([tf.keras.layers.Dense(3,activation='sigmoid')])
model.compile(optimizer=tf.keras.optimizers.SGD(lr=0.1),loss='sparse_categorical_crossentropy',metrics=['sparse_categorical_accuracy'])
model.fit(x_train,y_train,epochs=500,validation_freq=20,validation_split=0.2)
model.summary()

