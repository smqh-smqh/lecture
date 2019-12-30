import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    /**
     * 不重复集合求子集
     * @param : A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public static void main(String[] args) {
        int[] first = new int[]{1, 2, 3,4};
        ArrayList<ArrayList<Integer>> res = subsets(first);
        for(int i = 0; i < res.size(); i ++){
            System.out.println(res.get(i));
        }
    }
    public static ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        if(nums.length == 0 || nums == null)
            return res;
        Arrays.sort(nums); //排序
        dfs(nums, 0, item, res);  //递归调用
        res.add(new ArrayList<Integer>());  //最后加上一个空集
        return res;
    }
    public static void dfs(int[] nums, int start, ArrayList<Integer>item, ArrayList<ArrayList<Integer>>res){
        for(int i = start; i < nums.length; i ++){
            item.add(nums[i]);
            //item是以整数为元素的动态数组，而res是以数组为元素的数组，在这一步，当item增加完元素后，item所有元素构成一个完整的子串，再由res纳入
            res.add(new ArrayList<Integer>(item));
            dfs(nums, i + 1, item, res);
            item.remove(item.size() - 1);
        }
    }
}

