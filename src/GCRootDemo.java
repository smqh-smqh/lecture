/**在Java中，可用作GC Roots的对象有：
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中JNI（native方法）中引用的对象
 */

public class GCRootDemo {
    private byte[] bytes=new byte[10*1024*1024];
//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3=new GCRootDemo3(6);

    public static void m1(){
        GCRootDemo t1 =new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
        //内存
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机中的内存总量 本机的1/64
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量 本机的1/4
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");
    }
}

