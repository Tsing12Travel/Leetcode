package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) return res;

        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, 0, target, path, res);
        return res;
    }

    private void backtrack(int[] candidates, int curr, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {  // 找到一个合法组合
            res.add(new ArrayList<>(path));
            return;
        }
        // candidates 已从小到大排序，如果递归中发现 left<candidates[i]，由于后面的数字只会更大，所以无法把 left 减小到 0，可以直接返回
        if (curr == candidates.length || target < candidates[curr]) return;

        // 不选：直接递归到 dfs(i + 1, left)
        backtrack(candidates, curr + 1, target, path, res);

        // 选：递归到 dfs(i, left − candidates[i])。注意 i 不变，表示在下次递归中可以继续选 candidates[i]
        path.add(candidates[curr]);
        backtrack(candidates, curr, target - candidates[curr], path, res);
        path.removeLast();
    }


    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        No39_CombinationSum combinationSum = new No39_CombinationSum();
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
