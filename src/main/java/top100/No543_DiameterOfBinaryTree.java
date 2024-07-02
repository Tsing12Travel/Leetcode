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


    // ans 表示以 node 为起点，经过该点的最长路径点数数值
    int ans = 1;

    public int diameterOfBinaryTree2(TreeNode root) {
        depth(root);
        return ans - 1;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;  // 访问到空节点，返回 0

        int left = depth(node.left);  // 左子树深度
        int right = depth(node.right);  // 右子树深度

        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;  // node 节点的深度
    }


    int max;

    public int diameterOfBinaryTree3(TreeNode root) {
        max = 0;
        postTraverse(root);
        return max;
    }

    public int postTraverse(TreeNode root) {
        if (root == null) return 0;
        int left = postTraverse(root.left);
        int right = postTraverse(root.right);
        int temp = left + right;
        if (max < temp) max = temp;
        return left > right ? left + 1 : right + 1;  // root 的深度
    }
}
