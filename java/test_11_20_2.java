import java.util.Scanner;

public class test_11_20_2
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] in = new int[n];
        int[] out = new int[n];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            in[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            out[i] = input.nextInt();
        }
        input.close();
        boolean flag = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (in[j]==out[i]) {
                    order[i]=j;
                    break;
                }
            }
            //System.out.print(order[i]+" ");
        }
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(order[i]>order[j]){
                    for(int k = j; k+1 < n; k++){
                        if(order[k]<order[k+1]&&order[k]<order[j]){
                            flag=false;
                            break;
                        }
                    }
                    if(flag==false) break;
                }
            }
            if(flag==false) break;
        }
        System.out.println(flag);
    }
}
