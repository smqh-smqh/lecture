package singleton;

//在内部类被加载和初始化时，才创建Singleton6对象
//静态内部类不会随外部类加载和初始化而初始化

public class Singleton6 {
    private Singleton6(){}
    private static class Inner{
        private static final Singleton6 INSTANCE=new Singleton6();
    }
    public static Singleton6 getInstance(){
        return Inner.INSTANCE;
    }
}
