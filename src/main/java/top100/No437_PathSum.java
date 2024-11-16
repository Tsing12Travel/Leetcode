package top100;

import java.util.HashMap;
import java.util.Map;

public class No437_PathSum {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        return dfs(root, 0, targetSum, cnt);
    }

    public int dfs(TreeNode node, long s, int targetSum, Map<Long, Integer> cnt) {
        if (node == null) return 0;
        s += node.val;
        int ans = cnt.getOrDefault(s - targetSum, 0);
        cnt.merge(s, 1, Integer::sum);
        ans += dfs(node.left, s, targetSum, cnt);
        ans += dfs(node.right, s, targetSum, cnt);
        cnt.merge(s, -1, Integer::sum);  // 恢复现场
        return ans;
    }


    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) return 0;

        int res = rootSum(root, targetSum);
        res += pathSum2(root.left, targetSum);
        res += pathSum2(root.right, targetSum);
        return res;
    }

    private int rootSum(TreeNode root, long targetSum) {
        int res = 0;
        if (root == null) return 0;

        int val = root.val;
        if (val == targetSum) res++;

        res += rootSum(root.left, targetSum - val);
        res += rootSum(root.right, targetSum - val);
        return res;
    }


    public int pathSum3(TreeNode root, int targetSum) {
        // HashMap to store (prefix_sum, count) pairs
        Map<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);
        return dfs(root, 0, targetSum, prefixSums);
    }

    private int dfs(TreeNode node, int currentSum, int targetSum, Map<Integer, Integer> prefixSums) {
        if (node == null) {
            return 0;
        }

        // Update current sum
        currentSum += node.val;

        // Get the number of valid paths that end at this node
        int numPathsToCurr = prefixSums.getOrDefault(currentSum - targetSum, 0);

        // Update the prefixSums map
        prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);

        // Recurse to left and right subtrees
        numPathsToCurr += dfs(node.left, currentSum, targetSum, prefixSums);
        numPathsToCurr += dfs(node.right, currentSum, targetSum, prefixSums);

        // Remove the current sum from the map to backtrack
        prefixSums.put(currentSum, prefixSums.get(currentSum) - 1);

        return numPathsToCurr;
    }
}
