package upSteps;

import org.junit.Test;
/*
 *有n步台阶，每次只能走一步或两步，有多少种走法
 *方法调用自身称为递归
 * 优点：精简代码
 * 缺点：浪费空间
 * */
public class recursion {
    @Test
    public void test(){
        long start=System.currentTimeMillis();
        System.out.println(f(40));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    public int f(int n){
        if(n<1){
            throw new IllegalArgumentException(n+"不能小于1");
        }
        if(n==1||n==2){
            return n;
        }
        return f(n-1)+f(n-2);
    }
}
