package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No39_CombinationSum {
    /*「答案视角」。用 dfs(i, left) 来回溯，设当前枚举到 candidates[i]，剩余要选的元素之和为 left，考虑枚举下个元素是谁：
    在 [i, n − 1] 中枚举要填在 path 中的元素 candidates[j]，然后递归到 dfs(j, left − candidates[j])。
    注意这里是递归到 j 不是 j + 1，表示 candidates[j] 可以重复选取 */
    // 枚举选哪个
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) return res;

        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        dfs(candidates, 0, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int depth, int left, List<Integer> path, List<List<Integer>> res) {
        if (left == 0) {  // 找到一个合法组合
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝，剩下的数都比 left 大，直接返回
        if (left < candidates[depth]) return;

        for (int i = depth; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, i, left - candidates[i], path, res);
            path.removeLast();
        }
    }


    // 选或不选
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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


    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<Integer> state = new ArrayList<>(); // 状态（子集）
        Arrays.sort(candidates); // 对 candidates 进行排序
        int start = 0; // 遍历起始点
        List<List<Integer>> res = new ArrayList<>(); // 结果列表（子集列表）
        backtrack2(state, target, candidates, start, res);
        return res;
    }

    public void backtrack2(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，记录解
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        // 剪枝二：从 start 开始遍历，避免生成重复子集
        for (int i = start; i < choices.length; i++) {
            // 剪枝一：若子集和超过 target ，则直接结束循环
            // 这是因为数组已排序，后边元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 尝试：做出选择，更新 target, start
            state.add(choices[i]);
            // 进行下一轮选择
            backtrack2(state, target - choices[i], choices, i, res);
            // 回退：撤销选择，恢复到之前的状态
            state.removeLast();
        }
    }


    // 完全背包预处理 + 可行性剪枝
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        int n = candidates.length;
        // 完全背包
        boolean[][] f = new boolean[n + 1][target + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                f[i + 1][j] = f[i][j] || j >= candidates[i] && f[i + 1][j - candidates[i]];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 倒着递归，这样参数符合 f 数组的定义
        dfs(n - 1, target, candidates, f, ans, path);
        return ans;
    }

    private void dfs(int i, int left, int[] candidates, boolean[][] f, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) {
            // 找到一个合法组合
            ans.add(new ArrayList<>(path));
            return;
        }

        // 无法用下标在 [0, i] 中的数字组合出 left
        if (left < 0 || !f[i + 1][left]) {
            return;
        }

        // 不选
        dfs(i - 1, left, candidates, f, ans, path);

        // 选
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, f, ans, path);
        path.remove(path.size() - 1);
    }


    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        No39_CombinationSum combinationSum = new No39_CombinationSum();
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
