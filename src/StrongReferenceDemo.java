import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class StrongReferenceDemo {
    public static void strong(){
        Object o1=new Object();
        Object o2=o1;
        System.out.println(o1);
        System.out.println(o2);
        o1=null;
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(o2);
        System.gc();
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(o2);
    }

    public static void soft(){
        Object o1=new Object();
        SoftReference<Object> o2=new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(o2.get());
        o1=null;
        Byte[] bytes=new Byte[2*1024*1024];
        System.gc();
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(o2.get());
    }

    public static void weak(){
        Object o1=new Object();
        WeakReference<Object> o2=new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(o2.get());
        o1=null;
        //Byte[] bytes=new Byte[2*1024*1024];
        System.gc();
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(o2.get());
    }

    public static void phantom(){
        Object o1=new Object();
        ReferenceQueue<Object> referenceQueue=new ReferenceQueue<>();
        PhantomReference<Object> o2=new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(o2.get());
        System.out.println(referenceQueue.poll());
        o1=null;
        System.gc();
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(o2.get());
        System.out.println(referenceQueue.poll());
    }

    public static void main(String[] args) {
        //strong();
//        weak();
        phantom();
    }
}
