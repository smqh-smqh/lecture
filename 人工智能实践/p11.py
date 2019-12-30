# np.random.RandomState.rand()

import numpy as np

rdm=np.random.RandomState(1)
a=rdm.rand()
b=rdm.rand(2,3)
print("a:",a)
print("b:",b)