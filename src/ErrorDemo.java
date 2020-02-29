import com.sun.source.tree.EnhancedForLoopTree;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ErrorDemo {
    public static void StackOverFlowError(){
        StackOverFlowError();
    }

    public static void JavaHeapSpaceError() {
        //-Xms10m -Xmx10m
        byte[] bytes=new byte[10*1024*1024];
    }

    public static void GCOverHeadError() {
        /**
         * GC回收时间过长OutOfMemoryError,超过98%的时间用来GC只回收了不到2%的堆内存
         * -Xms10m -Xmx10m -XX:PrintGCDetails -XX:MaxDirectMemorySize=5m
         */
        int i=0;
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("****************i: "+i);
            e.printStackTrace();
            throw e;
        }
    }

    public static void DirectBufferMemoryError() throws InterruptedException {
        /**
         * -Xms10m -Xmx10m -XX:PrintGCDetails -XX:MaxDirectMemorySize=5m
         */
//        System.out.println("配置的maxDirectMemory: "+ (sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        Thread.sleep(3000);
        ByteBuffer bb=ByteBuffer.allocateDirect(6*1024*1024);
    }

    //不要在自己的机器上尝试
    public static void UnableCreateNewThreadDemo() {
        for (int i = 0;  ; i++) {
            System.out.println("************i: "+i);
            new Thread(()->{
                try{Thread.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }

    static class OOMTest{}
    public static void MetaSpaceOOM() {
        /**
         * java8及之后用Metaspace来代替永久代
         * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
         * 存放： 虚拟机加载的类信息  常量池 静态变量  即时编译后的代码
         */
        int i=0;
        try{
            while (true){
                i++;
//                Enhancer e=new Enhancer();
                //OOMTest....
            }
        } catch (Exception e) {
            System.out.println("*********多少次后发生了异常： "+i);
            e.printStackTrace();
        }
    }

}
