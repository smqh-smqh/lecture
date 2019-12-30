import java.util.Scanner;

public class test_11_20_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        int k = input.nextInt();
        input.close();
        boolean flag=false;
        for(int i=0;i<n;i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] == arr[j]&&j-i<=k) {
                    flag = true;
                    break;
                }
            }
            if(flag==true) break;
        }
        System.out.println(flag);
    }
}
