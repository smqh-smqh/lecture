package upSteps;

import org.junit.Test;

/*
*有n步台阶，每次只能走一步或两步，有多少种走法
* 利用方法的原值推出新值为迭代
* 优点：效率高
* 缺点：不简洁
 */
public class Iterater {
    @Test
    public void test(){
        long start=System.currentTimeMillis();
        System.out.println(loop(40));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    public int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int one = 2;
        int two = 1;
        int sum=0;

        for(int i=3;i<=n;i++){
            sum=two+one;
            two=one;
            one=sum;
        }
        return sum;
    }
}
