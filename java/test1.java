import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=input.nextInt();
        }
        int sum = input.nextInt();
        input.close();
        int total=0 ,k=0,min= (int) Float.POSITIVE_INFINITY;
        for(int i=0;i<n;i++){
            total+=arr[i];
            k++;
            if(total>=sum){
                total=0;
                if(k<min) min=k;
                k=0;
            }else if(i==n-1) min=0;
        }
        System.out.println(min);
    }
}
