package top100;

import java.util.ArrayList;
import java.util.List;

public class No78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len == 0) return res;

        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, path, res);
        return res;
    }

    private void dfs(int[] nums, int depth, List<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {  // 子集构造完毕
            res.add(new ArrayList<>(path));  // 复制 path
            return;
        }

        // 不选 nums[depth]
        dfs(nums, depth + 1, path, res);

        // 选 nums[depth]
        path.add(nums[depth]);
        dfs(nums, depth + 1, path, res);
        path.removeLast();  // 恢复现场
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        No78_Subsets subsets = new No78_Subsets();
        List<List<Integer>> res = subsets.subsets(nums);
        System.out.println(res);
    }
}
