package singleton;

//线程安全

public class Singleton5 {
    private static Singleton5 instance;
    private Singleton5(){}
    public static Singleton5 getInstance() throws InterruptedException {
        if (instance == null) {
            synchronized (Singleton5.class) {
                Thread.sleep(100);
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
