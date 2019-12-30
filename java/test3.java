import java.util.Scanner;

public class test3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if(arr[i]<0){
                for(int j=i;j>=0;j--){
                    if(arr[j]>0){
                        if(Math.abs(arr[i])<Math.abs(arr[j])){
                            arr[i]=0;
                            break;
                        }else if(Math.abs(arr[i])==Math.abs(arr[j])){
                            arr[i]=0;
                            arr[j]=0;
                            break;
                        }else arr[j]=0;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(0!=arr[i])
            System.out.print(arr[i]+" ");
        }
    }
}
