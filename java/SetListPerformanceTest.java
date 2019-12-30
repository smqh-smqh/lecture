import java.util.*;

public class SetListPerformanceTest {
    static final int N = 100;
    static final int M = 100000;

    public static void main(String[] args) {
        long t[]=new long[27];

        Collection<Integer> list1 = new ArrayList<>();
        Collection<Integer> list2 = new LinkedList<>();
        Collection<Integer> stack = new Stack<>();
        Collection<Integer> set1 = new HashSet<>();
        Collection<Integer> set2 = new TreeSet<>();
        Collection<Integer> set3 = new LinkedHashSet<>();
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new LinkedHashMap<String, String>();
        Map<String, String> map3 = new HashMap<String, String>();

        for (int i=0;i<M;i++){
            String s = String.valueOf(i);
            list1.add(i);
            list2.add(i);
            stack.add(i);
            set1.add(i);
            set2.add(i);
            set3.add(i);
            map1.put(s,s);
            map2.put(s,s);
            map3.put(s,s);
        }


        for(int i=0;i<10;i++) {

            t[0]+=getAddTime(list1);
//            System.out.println("-----------list1------------");
//            print(list1);
//            System.out.println();
            t[1]+=getSearchTime(list1);
            t[2]+=getRemoveTime(list1);
//            System.out.println("Add test time for hashset is " + getAddTime(list1) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(list1) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(list1) + " milliseconds");


            t[3]+=getAddTime(list2);
//            System.out.println("-----------list2------------");
//            print(list2);
//            System.out.println();
            t[4]+=getSearchTime(list2);
            t[5]+=getRemoveTime(list2);
//            System.out.println("Add test time for LinkedList is " + getAddTime(list2) + " milliseconds");
//            System.out.println("Search test time for LinkedList is " + getSearchTime(list2) + " milliseconds");
//            System.out.println("Remove test time for LinkedList is " + getRemoveTime(list2) + " milliseconds");


            t[6]+=getAddTime(stack);
            t[7]+=getSearchTime(stack);
            t[8]+=getRemoveTime(stack);
//            System.out.println("Add test time for hashset is " + getAddTime(stack) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(stack) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(stack) + " milliseconds");


            t[9]+=getAddTime(set1);
            t[10]+=getSearchTime(set1);
            t[11]+=getRemoveTime(set1);
//            System.out.println("Add test time for hashset is " + getAddTime(set1) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(set1) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(set1) + " milliseconds");


            t[12]+=getAddTime(set2);
            t[13]+=getSearchTime(set2);
            t[14]+=getRemoveTime(set2);
//            System.out.println("Add test time for hashset is " + getAddTime(set2) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(set2) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(set2) + " milliseconds");


            t[15]+=getAddTime(set3);
            t[16]+=getSearchTime(set3);
            t[17]+=getRemoveTime(set3);
//            System.out.println("Add test time for hashset is " + getAddTime(set3) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime(set3) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime(set3) + " milliseconds");


            t[18]+=getAddTime2(map1);
//            System.out.println("-----------map1------------");
//            print2(map1);
//            System.out.println();
            t[19]+=getSearchTime2(map1);
            t[20]+=getRemoveTime2(map1);
//            System.out.println("Add test time for map1 is " + getAddTime2(map1) + " milliseconds");
//            System.out.println("Search test time for map1 is " + getSearchTime2(map1) + " milliseconds");
//            System.out.println("Remove test time for map1 is " + getRemoveTime2(map1) + " milliseconds");


            t[21]+=getAddTime2(map2);
            t[22]+=getSearchTime2(map2);
            t[23]+=getRemoveTime2(map2);
//            System.out.println("Add test time for hashset is " + getAddTime2(map2) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime2(map2) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime2(map2) + " milliseconds");


            t[24]+=getAddTime2(map3);
            t[25]+=getSearchTime2(map3);
            t[26]+=getRemoveTime2(map3);
//            System.out.println("Add test time for hashset is " + getAddTime2(map3) + " milliseconds");
//            System.out.println("Search test time for hashset is " + getSearchTime2(map3) + " milliseconds");
//            System.out.println("Remove test time for hashset is " + getRemoveTime2(map3) + " milliseconds");
        }
       for(int i=0;i<27;i++){
           if(i%3==0) System.out.println();
           //System.out.print(t[i]/10+" ");
           System.out.print(String.format("%.2f",t[i]*1.00/10/1000)+" ");//获取微秒数
       }

    }

    public static void print2(Map<String,String> m){
        Set<String> keySet = m.keySet();
        Iterator<String> it =keySet.iterator();
        while(it.hasNext()){
            String key = it.next();
            //通过key获取对应的value
            String value = m.get(key);
            System.out.println(key+"="+value);
        }
    }

    public static void print(Collection<Integer> c){
        for(int i: c){
            System.out.print(i+" ");
        }
    }

    public static long getAddTime(Collection<Integer> c) {
        long startTime = System.nanoTime();
        for (int i=M;i<N+M;i++){
            c.add(i);
        }
        return System.nanoTime() - startTime;
    }

    public static long getSearchTime(Collection<Integer> c) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            c.contains((int)Math.random()*2*M);
        }
        return System.nanoTime() - startTime;
    }

    public static long getRemoveTime(Collection<Integer> c) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            c.remove(i);
        }
        return System.nanoTime() - startTime;
    }
    public static long getAddTime2(Map<String,String> m) {
        long startTime = System.nanoTime();
        for (int i=M;i<N+M;i++){
            String s = String.valueOf(i);
            m.put(s,s);
        }
        return System.nanoTime() - startTime;
    }

    public static long getSearchTime2(Map<String,String> m) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            String s = String.valueOf((int)Math.random()*2*M);
            m.get(s);
        }
        return System.nanoTime() - startTime;
    }

    public static long getRemoveTime2(Map<String,String> m) {
        long startTime = System.nanoTime();
        for (int i=0;i<N;i++){
            String s = String.valueOf(i);
            m.remove(s);
        }
        return System.nanoTime() - startTime;
    }
}

