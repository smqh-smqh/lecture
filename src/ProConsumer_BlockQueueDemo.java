import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean flag=true;
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data=null;
        boolean retValue;
        while (flag){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue)
                System.out.println(Thread.currentThread().getName()+"\t 插入队列成功"+data);
            else
                System.out.println(Thread.currentThread().getName()+"\t 插入队列失败"+data);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t 老板叫停");
    }
    public void myConsumer() throws Exception{
        String result=null;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                flag=false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有取到，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费"+result+"成功");
        }
     }

     public void stop() throws Exception{
        this.flag=false;
     }
}

public class ProConsumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Producer").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            System.out.println();
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println("5秒时间到，main线程叫停");
        myResource.stop();
    }
}
