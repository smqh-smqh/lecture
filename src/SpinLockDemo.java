import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void mylock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        while (!atomicReference.compareAndSet(null,thread))
        {

        }
    }

    public void myUnlock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock()");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.mylock();
            spinLockDemo.myUnlock();
        },"BB").start();
    }
}
