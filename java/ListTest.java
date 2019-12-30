import java.util.*;

public class ListTest {
        static final int N = 100;
        static final int M = 100000;

        public static void main(String[] args) {
            long t[]=new long[27];
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new LinkedList<>();
            for (int i=0;i<M;i++){
                list1.add(i);
                list2.add(i);
            }
            for(int i=0;i<10;i++) {
                t[0] += getAddTime1(list1);
                t[4] += getRemoveTime1(list1);
                t[1] += getAddTime2(list1);
                t[5] += getRemoveTime2(list1);
                t[2] += getAddTime3(list1);
                t[3] += getSearchTime(list1);
                t[6] += getRemoveTime3(list1);
//            System.out.println("Add test time for hashset is " + getAddTime(list1) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(list1) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(list1) + " milliseconds");


                t[7] += getAddTime1(list2);
                t[11] += getRemoveTime1(list2);
                t[8] += getAddTime2(list2);
                t[12] += getRemoveTime2(list2);
                t[9] += getAddTime3(list2);
                t[10] += getSearchTime(list2);
                t[13] += getRemoveTime3(list2);
//            System.out.println("Add test time for LinkedList is " + getAddTime(list2) + " milliseconds");
//            System.out.println("Search test time for LinkedList is " + getSearchTime(list2) + " milliseconds");
//            System.out.println("Remove test time for LinkedList is " + getRemoveTime(list2) + " milliseconds");
            }
                for(int i=0;i<14;i++){
                if(i%7==0) System.out.println();
                //System.out.print(t[i]+" ");
                System.out.print(String.format("%.2f",t[i]*1.00/10/1000)+" ");//获取微秒数
            }

        }

        public static long getAddTime1(List<Integer> c) {
            long startTime = System.nanoTime();
            for (int i=M;i<N+M;i++){
                c.add(0,i);
            }
            return System.nanoTime() - startTime;
        }
        public static long getAddTime2(List<Integer> c) {
            long startTime = System.nanoTime();
            for (int i=M;i<N+M;i++){
                c.add(c.size(),i);
            }
            return System.nanoTime() - startTime;
        }
        public static long getAddTime3(List<Integer> c) {
            long startTime = System.nanoTime();
            for (int i=M;i<N+M;i++){
                c.add(c.size()/2,i);
            }
            return System.nanoTime() - startTime;
        }

        public static long getSearchTime(List<Integer> c) {
            long startTime = System.nanoTime();
            for (int i=0;i<N;i++){
                c.contains((int)Math.random()*2*M);
            }
            return System.nanoTime() - startTime;
        }

        public static long getRemoveTime1(List<Integer> c) {
            long startTime = System.nanoTime();
            for (int i=0;i<N;i++){
                c.remove(0);
            }
            return System.nanoTime() - startTime;
        }
    public static long getRemoveTime2(List<Integer> c) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            c.remove(c.size()-1);
        }
        return System.nanoTime() - startTime;
    }
    public static long getRemoveTime3(List<Integer> c) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            c.remove(c.size()/2);
        }
        return System.nanoTime() - startTime;
    }
}
