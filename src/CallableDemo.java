import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" call() invoked");
        //TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread());
        //#######只算一次##########
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        System.out.println(Thread.currentThread().getName()+"***********");

        int result1=100;
        while (!futureTask.isDone()){}

        int result2=futureTask.get();
        System.out.println("*****result= "+(result1+result2));
    }
}
