package top100;

public class No98_IsValidBST {
    // 前序遍历
    public boolean isValidBST(TreeNode root) {
        // 定义两个边界，这时这道题的核心，我们在一次次的递归中也在不断地为每个节点缩小上下限的范围，一旦超出便是 false
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        return isValidBST(root, min, max);
    }

    public boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) return true;

        int node_val = node.val;
        // 左节点永远不会大于 node，右节点永远不会小于node，故 node 的值需要在 (left, right) 内
        if (node_val < left || node_val > right) return false;

        // node 节点左子节的的值区间为 (left, node.val)
        // node 节点右子节点的值区间为 (node.val, right)
        return isValidBST(node.left, left, node_val) && isValidBST(node.right, node_val, right);
    }


    private long pre = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST2(root.left) || root.left.val <= pre) return false;

        pre = root.val;
        return isValidBST2(root.right);
    }


    long max = Long.MIN_VALUE;
    long min = Long.MAX_VALUE;
    public boolean isValidBST3(TreeNode root) {
        return dfs(root)[1] != max;
    }

    private long[] dfs(TreeNode node) {
        if (node == null) return new long[]{min, max};

        long[] left = dfs(node.left);
        long[] right = dfs(node.right);
        long node_val = node.val;

        // 也可以在递归完左子树之后立刻判断，如果发现不是二叉搜索树，就不用递归右子树了
        if (node_val <= left[1] || node_val >= right[0]) {
            return new long[]{min, max};
        }

        return new long[]{Math.min(left[0], node_val), Math.max(right[1], node_val)};
    }
}
