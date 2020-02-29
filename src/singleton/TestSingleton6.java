package singleton;

import java.util.concurrent.*;

public class TestSingleton6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton6 s4=Singleton6.getInstance();
//        Singleton6 s=Singleton6.getInstance();
//        System.out.println(s4==s);
//        System.out.println(s4);
//        System.out.println(s);

        Callable<Singleton6> c=new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                return Singleton6.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton6> f1=es.submit(c);
        Future<Singleton6> f2=es.submit(c);

        Singleton6 s1=f1.get();
        Singleton6 s2=f2.get();

        System.out.println(s1==s2);
        System.out.println(s1);
        System.out.println(s2);
        es.shutdown();
    }
}
