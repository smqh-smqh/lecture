import javax.swing.plaf.SplitPaneUI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t ######invoked sendEmail()");
    }

    Lock lock=new ReentrantLock();
    public void run(){
        get();
    }
    public void get(){
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            //lock与unlock必须匹配
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t ######invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone=new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        Thread t3=new Thread(phone,"t3");
        Thread t4=new Thread(phone,"t4");
        t3.start();
        t4.start();

    }
}
