# 利用sequential结构训练并测试fashion_mnist

import tensorflow as tf
import os
# from keras.layers import Conv2D,BatchNormalization,Activation,MaxPool2D,Dropout, Flatten,Dense

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

model_save_path = './checkpoint/fashion.tf'

fashion_mnist = tf.keras.datasets.fashion_mnist
(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
x_train = x_train.reshape(x_train.shape[0], 28, 28, 1)
x_test = x_test.reshape(x_test.shape[0], 28, 28, 1)
x_train, x_test = x_train / 255.0, x_test / 255.0

model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(input_shape=(28, 28, 1),filters=32,kernel_size=(5, 5),padding='same'),  # 卷积层
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

if os.path.exists(model_save_path+'.index'):
    print('-------------load the model-----------------')
    model.load_weights(model_save_path)
for i in range(5):
    model.fit(x_train, y_train, epochs=1,batch_size=32, validation_data=(x_test, y_test), validation_freq=2)
    model.save_weights(model_save_path, save_format='tf')

model.summary()

file = open('./weights.txt', 'w')  # 参数提取
for v in model.trainable_variables:
    file.write(str(v.name) + '\n')
    file.write(str(v.shape) + '\n')
    file.write(str(v.numpy()) + '\n')
file.close()
