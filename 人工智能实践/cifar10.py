# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import tensorflow as tf
import os
from tensorflow.keras.layers import Conv2D, BatchNormalization, Activation, MaxPool2D, Dropout, Flatten, Dense
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

model_save_path = './checkpoint/cifar10.ckpt'

train_path = './cifar-10/train/'
test_path = './cifar-10/test/'


def generateds(path):
    labels = os.listdir(path)
    x, y = [], []
    for i in range(len(labels)):
        print("{} stands for {}".format(i, labels[i]))
        categorical_path = path + labels[i]
        for img_name in os.listdir(categorical_path):
            img_path = categorical_path + '/' + img_name
            img = Image.open(img_path)
            img = np.array(img)
            img = img / 255.0
            x.append(img)
            y.append(i)
    x = np.array(x)
    y = np.array(y)
    y = y.astype(np.int64)
    print("Generate Dateset successfully!")
    return x, y


x_train, y_train = generateds(train_path)
x_test, y_test = generateds(test_path)
x_train = tf.convert_to_tensor(x_train)
x_test = tf.convert_to_tensor(x_test)
y_train = tf.convert_to_tensor(y_train)
y_test = tf.convert_to_tensor(y_test)

model = tf.keras.models.Sequential([
    Conv2D(filters=32, kernel_size=(5, 5), padding='same', input_shape=(32, 32, 3)),
    BatchNormalization(),
    Activation('relu'),
    MaxPool2D(pool_size=(2, 2), strides=2, padding='same'),
    Dropout(0.2),

    Conv2D(64, kernel_size=(5, 5), padding='same'),
    BatchNormalization(),
    Activation('relu'),
    MaxPool2D(pool_size=(2, 2), strides=2, padding='same'),
    Dropout(0.2),

    Flatten(),
    Dense(512, activation='relu'),
    Dropout(0.2),
    Dense(10, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['sparse_categorical_accuracy'])

if os.path.exists(model_save_path + '.index'):
    print('-------------load the model-----------------')
    model.load_weights(model_save_path)

cp_callbacks = tf.keras.callbacks.ModelCheckpoint(filepath=model_save_path,
                                                  save_weights_only=True,
                                                  verbose=1)
model.fit(x_train, y_train, epochs=5, batch_size=32,
          shuffle=True,
          callbacks=[cp_callbacks],
          validation_data=(x_test, y_test),
          validation_freq=2)

model.summary()

# file = open('./weights.txt', 'w')  # 参数提取
# for v in model.trainable_variables:
# 	file.write(str(v.name) + '\n')
#     file.write(str(v.shape) + '\n')
#     file.write(str(v.numpy()) + '\n')
# file.close()
