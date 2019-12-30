import java.util.Scanner;

public class exercise_9_13 {
    static class Location{
        int row,column;
        double maxValue;
        public static Location locateLargest(float[][] a){
            Location l = new Location();
            l.maxValue=a[0][0];

            for(int i=0;i<a.length;i++){
                for(int j=0;j<a[i].length;j++){
                    if(a[i][j]>l.maxValue){
                        l.maxValue=a[i][j];
                        l.row=i;
                        l.column=j;
                    }
                }
            }
            return l;
        }

    }
    public static void main(String[] args){
        System.out.println("Enter the numbers of rows and columns in the arr:");
        Scanner input = new Scanner(System.in);
        int r=input.nextInt();
        int c=input.nextInt();
        float arr[][]=new float[r][c];
        System.out.println("Enter the arr:");
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                arr[i][j]=input.nextFloat();
            }
        }
        Location lc = new Location();
        lc=lc.locateLargest(arr);
        System.out.println("The location of the largest element is "+lc.maxValue+" at ("+lc.row+","+lc.column+")");
    }
}
