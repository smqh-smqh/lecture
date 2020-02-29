import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class volatileTest {
    public int num;
    //public volatile int num;
    public void setNum(){
        this.num=60;
    }
    public void NumPlusPlus(){
        num++;
    }
    AtomicInteger atomicInteger=new AtomicInteger();
    public void atomicIntegerPlus(){
        atomicInteger.getAndIncrement();
    }

    //可见性
    public static void seekByVolatile(){
        volatileTest my = new volatileTest();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            my.setNum();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:"+my.num);
        }, "AAA").start();
        while (my.num==0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value: "+my.num);
    }

    public static void main(String[] args){
        //seekByVolatile();
        //原子性
        volatileTest myData=new volatileTest();
        for(int i=1;i<=20;i++){
            new Thread(()->{
                for(int j=1;j<=1000;j++){
                    myData.NumPlusPlus();
                    myData.atomicIntegerPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int number value:"+myData.num);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger number value:"+myData.atomicInteger);
    }

}
