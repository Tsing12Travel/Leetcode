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


    int diameter4 = 0;
    public int diameterOfBinaryTree4(TreeNode root) {
        if (root == null) return 0;
        depth4(root);
        return diameter4;
    }

    private int depth4(TreeNode root) {
        if (root == null) return 0;

        int left = depth4(root.left);
        int right = depth4(root.right);

        // 更新最大直径：节点之间的路径长度 = 左子树深度 + 右子树深度
        diameter4 = Math.max(diameter4, left + right);
        // 返回节点为根的子树的最大深度
        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        No543_DiameterOfBinaryTree solution = new No543_DiameterOfBinaryTree();
        System.out.println("The diameter of the binary tree is: " + solution.diameterOfBinaryTree4(root));
    }
}
