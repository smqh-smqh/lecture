import java.util.Scanner;

public class test_12_4_3 {
    public static int valid(int crr[]) {
        for (int i = 100; i >= 0; i--) {
            if (crr[i] != 0) {
                return i;
            }
        }
        return 100;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        int[] arr = new int[100];
        int[] brr = new int[100];
        int[] crr = new int[101];
        int r = 0;
        int count = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            arr[count++] = Integer.parseInt(a.substring(i, i + 1));
        }
        count = 0;
        for (int i = b.length() - 1; i >= 0; i--) {
            brr[count++] = Integer.parseInt(b.substring(i, i + 1));
        }
        for (int i = 0; i < 100; i++) {
            crr[i] = (arr[i] + brr[i]) % 10 + r;
            if (arr[i] + brr[i] > 10) {
                r = 1;
            } else {
                r = 0;
            }
        }
        int x = valid(crr);
        for (int i = x; i >= 0; i--) {
            System.out.print(crr[i]);
        }
    }
}
