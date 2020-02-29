import javax.annotation.processing.Generated;
import java.util.concurrent.CountDownLatch;


public class CountLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国被灭");
                countDownLatch.countDown();
            },CountryEnum.ret(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t##秦统一天下");
    }
}
