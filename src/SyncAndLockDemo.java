import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number=1;//a1 b2 c3
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            //多线程必须用while
            while (number!=1){
                //1.等待
                c1.await();
            }
            //2.干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=2;
            //3.通知唤醒
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            //多线程必须用while
            while (number!=2){
                //1.等待
                c2.await();
            }
            //2.干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            //3.通知唤醒
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            //多线程必须用while
            while (number!=3){
                //1.等待
                c3.await();
            }
            //2.干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=1;
            //3.通知唤醒
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class SyncAndLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource=new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
