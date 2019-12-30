import java.util.Scanner;

public class test_12_4_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = b[i][j] =input.nextInt();
            }
        }
        input.close();
        if(m==0){

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    if(i==j){

                        System.out.print(1+" ");

                    }else{

                        System.out.print(0+" ");

                    }

                }

                System.out.println();

            }

        }else if(m==1){

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    System.out.print(a[i][j]);

                }

                System.out.println();

            }

        }else{

            for(int z =1;z<m;z++){

                int [][]tmp = new int[n][n];

                for(int i=0;i<n;i++){

                    for(int j=0;j<n;j++){

                        int add =0;

                        for(int y=0;y<n;y++){

                            add+=a[i][y]*b[y][j];

                        }

                        tmp[i][j]=add;



                    }

                }

                b=tmp;

            }

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    System.out.print(b[i][j]+" ");

                }
                System.out.println();
            }
        }
    }
}
