package singleton;

/**Singleton单例模式即整个类中只有一个实例对象可被获取和使用
    1.构造器私有化
    2.含有一个该类的静态变量来保存这个实例
    3.对外提供获取该实例的方式
 饿汉模式：1 2 3 不需要时也创建对象实例
 饱汉模式：4 5 6 需要时才创建对象实例
*/

public class Singleton1 {
    public static final Singleton1 INGLETON=new Singleton1();
    private Singleton1(){}
}
