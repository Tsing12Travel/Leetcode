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
}
