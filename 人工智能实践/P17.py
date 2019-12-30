# !/user/bin/env python
# coding:utf-8
# Author:wangyx

import tensorflow as tf
import numpy as np
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'  # 只显示error，不显示其他信息
a = np.arange(0, 5)
aa = tf.convert_to_tensor(a,dtype=tf.int32)
print(a)
print(aa)