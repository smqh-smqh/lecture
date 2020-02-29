import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ContainerNotSafeDAEMO {
    public static void main(String[] args){
        //new an empty arraylist with capacity 10
//        new ArrayList<Integer>().add(1);
//        List<String> list = Arrays.asList("a","b","c");
//        list.forEach(System.out::println);

//        List<String> list2=new ArrayList<>();
        //解决方案一：
//        List<String> list2=new Vector<>();
        //解决方案二：
//        List<String> list2=Collections.synchronizedList(new ArrayList<>());
        //解决方案三：
        List<String> list2=new CopyOnWriteArrayList<>();
        for(int i=1;i<=30;i++){
            new Thread(()->{
                list2.add(UUID.randomUUID().toString().substring(0,8));
//                try{ TimeUnit.NANOSECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(list2);
            },String.valueOf(i)).start();
        }
        //??java.util.ConcurrentModificationException
    }
}
