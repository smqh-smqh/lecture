# !/user/bin/env python
# coding:utf-8
# Author:wangyx

from PIL import Image
import numpy as np
import tensorflow as tf
import os
import matplotlib.pyplot as plt
from keras_preprocessing.image import ImageDataGenerator
# from keras.layers import Conv2D,BatchNormalization,Activation,MaxPool2D,Dropout, Flatten,Dense

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
np.set_printoptions(threshold=float('inf'))
model_save_path = './checkpoint/cifar-10.tf'
load_pretrain_model = False

test_path='./cifar-10/test/'
train_path='./cifar-10/train/'
kinds=next(os.walk(train_path))[1]

def generateds(kinds, path):
    x=[]
    y=[]
    for kind in kinds:
        img_names=next(os.walk(path+str(kind)))[2]
        for img_name in img_names:
            img_path=path+kind+'/'+img_name
            img=Image.open(img_path)
            img=np.array(img)
            x.append(img/255.0)
            y.append(kinds.index(kind))
    x = np.array(x)
    y = np.array(y)
    y = y.astype(np.int64)
    return x,y

x_train,y_train=generateds(kinds, train_path)
x_test,y_test=generateds(kinds, test_path)
x_train=tf.convert_to_tensor(x_train)
x_test=tf.convert_to_tensor(x_test)
y_train = tf.convert_to_tensor(y_train)
y_test = tf.convert_to_tensor(y_test)

#数据增强
image_gen_train = ImageDataGenerator(
                                     rescale=1./255,#归至0～1
                                     rotation_range=45,#随机45度旋转
                                     width_shift_range=.15,#宽度偏移
                                     height_shift_range=.15,#高度偏移
                                     horizontal_flip=True,#水平翻转
                                     zoom_range=0.5#将图像随机缩放到50％
                                     )
image_gen_train.fit(x_train)


model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(input_shape=(32, 32, 3),filters=32,kernel_size=(5, 5),padding='same'),  # 卷积层
    tf.keras.layers.BatchNormalization(),  # BN层
    tf.keras.layers.Activation('relu'),  # 激活层
    tf.keras.layers.MaxPool2D(pool_size=(2, 2), strides=2, padding='same'),  # 池化层
    tf.keras.layers.Dropout(0.2),  # dropout层

    tf.keras.layers.Conv2D(64, kernel_size=(5,5), padding='same'),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Activation('relu'),
    tf.keras.layers.MaxPool2D(pool_size=(2,2), strides=2, padding='same'),
    tf.keras.layers.Dropout(0.2),

    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.Dense(10, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['sparse_categorical_accuracy'])

#断点续训
if os.path.exists(model_save_path+'.index'):
    print('-------------load the model-----------------')
    model.load_weights(model_save_path)
for i in range(2):
    history = model.fit(x_train, y_train, batch_size=32, epochs=2,
                        validation_data=(x_test, y_test), shuffle=True,validation_freq=2)
    model.save_weights(model_save_path, save_format='tf')
model.summary()

#参数提取
file = open('./weights.txt', 'w')  # 参数提取
for v in model.trainable_variables:
    file.write(str(v.name) + '\n')
    file.write(str(v.shape) + '\n')
    file.write(str(v.numpy()) + '\n')
file.close()

#acc和loss可视化
acc = history.history['sparse_categorical_accuracy']
val_acc = history.history['val_sparse_categorical_accuracy']

loss = history.history['loss']
val_loss = history.history['val_loss']

plt.figure(figsize=(8, 8))
plt.subplot(1, 2, 1)
plt.plot(acc, label='Training Accuracy')
plt.plot(val_acc, label='Validation Accuracy')
plt.legend(loc='lower right')
plt.title('Training and Validation Accuracy')

plt.subplot(1, 2, 2)
plt.plot(loss, label='Training Loss')
plt.plot(val_loss, label='Validation Loss')
plt.legend(loc='upper right')
plt.title('Training and Validation Loss')
plt.show()

