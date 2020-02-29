public class test3 {
    static int s;
    int i;
    int j;
    {
        int i=1;
        i++;
        j++;
        s++;
    }
    public void test(int j){
        j++;
        i++;
        s++;
    }
    public static void main(String[] args){
        test3 t1=new test3();
        test3 t2=new test3();
        t1.test(10);
        t1.test(20);
        t2.test(30);
        System.out.println(t1.i+","+t1.j+","+t1.s);
        System.out.println(t2.i+","+t2.j+","+t2.s);
    }

}
