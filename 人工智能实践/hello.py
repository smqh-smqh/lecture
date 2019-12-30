# Ctrl + Alt + L 去掉波浪，规范代码
h = ['a','b','c','d'];
for i in h:
    print(i)
    for j in h:
        print(j)

for i in range(0,5):
    print("hello world %s " %i)

x=1
y=1
while (x<5 and y<6):
    x=x+1
    y=y+2
    print(x,y)

nums={(i,i+1):i for i in range(0,10)}
print(nums)