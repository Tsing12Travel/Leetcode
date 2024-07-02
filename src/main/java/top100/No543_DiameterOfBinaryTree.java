package top100;

public class No543_DiameterOfBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode node) {
        if (node == null) return -1;

        int left = dfs(node.left) + 1;
        int right = dfs(node.right) + 1;

        diameter = Math.max(diameter, left + right);
        return Math.max(left, right);
    }
}
