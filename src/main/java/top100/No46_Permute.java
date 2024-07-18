package top100;

import java.util.ArrayList;
import java.util.List;

public class No46_Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) return res;

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        dfs(nums, 0, used, path, res);

        return res;
    }

    private void dfs(int[] nums, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        No46_Permute permute = new No46_Permute();
        List<List<Integer>> res = permute.permute(nums);
        System.out.println(res);
    }
}
