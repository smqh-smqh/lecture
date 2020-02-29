/**
 * 判断这个常量是否存在于常量池。
 *   如果存在
 *    判断存在内容是引用还是常量，
 *     如果是引用，
 *      返回引用地址指向堆空间对象，
 *     如果是常量，
 *      直接返回常量池常量
 *   如果不存在，
 *    将当前对象引用复制到常量池,并且返回的是当前对象的引用
 */
public class InternDemo {
    public static void main(String[] args) {
        String aa = "AA";//设置常量AA到常量池
        String bb = "BB";//设置常量BB到常量池
        String ccdd = "CC"+"DD";//设置常量CCDD到常量池
        String neeff = new String("EE")+new String("FF");//设置EE和FF到常量池。并且添加EE、FF和EEFF对象到堆
        String aabb = aa+bb;//添加AABB对象到堆
        String gghh = "GG"+new String("HH");//设置GG和HH常量到常量池,设置HH和GGHH对象到堆
//         aa.intern();//啥事都不做，返回AA常量
//         ccdd.intern();//啥事都不做，返回CCDD常量
//         neeff.intern();//添加EEFF对象的引用到常量池，并返回EEFF对象
//         aabb.intern();//添加AABB对象的引用到常量池，并返回AABB对象
//         gghh.intern();//添加GGHH对象的引用到常量池，并返回GGHH对象
        System.out.println(aa.intern()==aa); //true
        System.out.println(neeff.intern()=="EEFF");//true
        System.out.println("EEFF"==neeff);//true
        String nccdd = new String("CCDD");
        System.out.println(ccdd==nccdd);//false
        System.out.println(ccdd==nccdd.intern());//true
        System.out.println(aabb.intern()==aabb);//true
        System.out.println(gghh==gghh.intern());//true
    }
}
