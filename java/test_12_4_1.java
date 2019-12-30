import java.util.Scanner;

public class test_12_4_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        int sum = input.nextInt();
        int length=0,total=0,begin=0;
        input.close();
        for (int i = 0; i < n; i++) {
            begin=i;
            total = 0;
            for (int j = i; j < n; j++){
                total+=arr[j];
                if(total>=sum) {
                    if (length == 0||length > j - begin+1) {
                        length = j - begin + 1;
                    }
                    break;
                }
            }
        }
        System.out.println(length);
    }
}
