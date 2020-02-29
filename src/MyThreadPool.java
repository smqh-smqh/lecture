import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());//四种策略
        try {
            //最大8，扩容6开始
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
