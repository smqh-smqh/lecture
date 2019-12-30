import java.util.HashMap;
import java.util.Scanner;

import static java.lang.constant.ConstantDescs.NULL;

public class test_12_4_5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        HashMap hm=new HashMap();
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
            if(NULL==hm.get(arr[i]))
            hm.put(arr[i],0);
            else hm.put(arr[i],(int)(hm.get(arr[i]))+1);
        }
        input.close();

    }
}
