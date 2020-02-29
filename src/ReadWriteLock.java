import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入"+key);
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取");
            try{
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwlock.readLock().unlock();
        }
    }

    public void clearMap(){
        map.clear();
    }
}

public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for(int i=1;i<=5;i++){
            final int tempInt=i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

//        try{
//            TimeUnit.MILLISECONDS.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for(int i=1;i<=5;i++){
            final int tempInt=i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
