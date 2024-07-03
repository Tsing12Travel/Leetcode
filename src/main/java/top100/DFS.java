package top100;

public class DFS {
    public void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        dfs(root.right);
    }
}
