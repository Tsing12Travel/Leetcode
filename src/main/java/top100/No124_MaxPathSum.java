package top100;

public class No124_MaxPathSum {
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;  // 没有节点，返回 0

        int left = dfs(node.left);  // 左子树最大链和
        int right = dfs(node.right);  // 右子树最大链和

        ans = Math.max(ans, node.val + left + right);  // 两条拼成路径

        return Math.max(Math.max(left, right) + node.val, 0);  // 当前子树最大链和
    }


    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子树的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);  // 如果左子树贡献为负，取0（不选左子树）
        int rightGain = Math.max(maxGain(node.right), 0); // 如果右子树贡献为负，取0（不选右子树）

        // 计算当前节点的最大路径和
        int currentMaxPath = node.val + leftGain + rightGain;

        // 更新全局最大路径和
        maxSum = Math.max(maxSum, currentMaxPath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
