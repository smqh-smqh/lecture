import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class teee {
    public static void main(String[] args) {
        //int a[9]={1};
        //int[] a=new int[1];
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new PrintChar('a',100));
        executor.execute(new PrintChar('b',100));
//        executor.execute(new PrintNum('a',100));

        executor.shutdown();
    }

    static class PrintChar implements Runnable{
        int time;
        char c;
        public PrintChar(char c,int times){
            this.c=c;
            this.time=times;
        }

        @Override
        public void run() {
            for(int i=0;i<time;i++){
                System.out.print(c);
            }

        }
    }
}

