import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class exercise_12_15 {
    public static void main(String[] args) throws IOException
    {
        File file = new File("Exercise12_15.txt");
        //System.out.println("hhh "+Math.random());
        PrintWriter output = new PrintWriter(file);
        for(int i=0;i<100;i++){
            output.print((int)(Math.random()*100)+" ");
        }
        output.close();
        Scanner input = new Scanner(file);
        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i]=input.nextInt();
        }
        input.close();
        Arrays.sort(arr);
        //System.out.println("aaa "+Math.random());
        for(int i=0;i<100;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
