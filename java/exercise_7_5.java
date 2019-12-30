import java.util.Scanner;

public class exercise_7_5 {
    public static void main(String[] args){
        System.out.println("Enter ten numbers:");
        Scanner input = new Scanner(System.in);
        int num,k=0;
        int arr[] = new int[10];
        for(int i=0;i<10;i++){
            num=input.nextInt();
            if(!exist(arr,num)){
                arr[k++]=num;
            }
        }
        System.out.println("The number of distinct number is "+k);
        System.out.print("The distinct number are: ");
        for(int j=0;j<k;j++){
            System.out.print(arr[j]+" ");
        }
    }
    public static boolean exist(int arr[], int t){
        for(int j=0;j<arr.length;j++){
            if(t==arr[j])
                return true;
        }
        return false;
    }
}
