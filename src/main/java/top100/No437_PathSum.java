package top100;

import java.util.HashMap;
import java.util.Map;

public class No437_PathSum {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        return dfs(root, 0,targetSum,cnt);
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
}
