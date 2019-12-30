import java.util.Scanner;

public class test2 {
    public static void print(int m1,int m2,int n1,int n2,int[][] arr){
        for(int i=m1;i<m2;i++)
            System.out.print(arr[n1][i]+" ");
        for(int j=n1+1;j<n2-1;j++)
            System.out.print(arr[j][m2-1]+" ");
        for(int i=m2-1;i>m1;i--)
            System.out.print(arr[n2-1][i]+" ");
        for(int j=n2-1;j>n1;j--)
            System.out.print(arr[j][m1]+" ");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.nextInt();
            }
        }
        int m1=0,n1=0;
        while(m1<n&&n1<m) {
            print(m1, n, n1, m, arr);
            m1++;
            n--;
            n1++;
            m--;
        }
    }
}
