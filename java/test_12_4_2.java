import java.util.Arrays;
import java.util.Scanner;

public class test_12_4_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        input.close();
        int[] sortedArr = Arrays.copyOf(nums,nums.length);
        Arrays.sort(sortedArr);
        int i = 0,j = nums.length - 1;
        while(nums[i] == sortedArr[i] && i < j)
            i++;
        while(nums[j] == sortedArr[j] && i < j)
            j--;
        if(i == j)
            System.out.println("0") ;
        else
            System.out.println(j - i + 1);
    }
}
