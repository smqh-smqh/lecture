package Initializer;

/*先初始化父类
在初始化子类
类初始化：
1.静态变量和块(5)(1)(10)(6)
实例初始化
1.非静态变量和块(6)(9)(3)(2)(9)(8)(7)
2.构造方法
 */

public class Son extends Father{
    int i=test();
    static int j=method();
    static {
        System.out.print("(6)");
    }
    Son(){
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }
    public int test(){
        System.out.print("(9)");
        return 1;
    }
    public static int method(){
        System.out.print("(10)");
        return 1;
    }
    public static void main(String[] args){
        Son s1=new Son();
        System.out.println();
        Son s2=new Son();
    }
}
