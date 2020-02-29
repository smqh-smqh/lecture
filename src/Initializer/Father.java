package Initializer;

public class Father {
    int i=test();
    static int j=method();
    static {
        System.out.print("(1)");
    }
    Father(){
        System.out.print("(2)");
    }
    {
        System.out.print("(3)");
    }
    public int test(){
        System.out.print("(4)");
        return 1;
    }
    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}

