package top100;

import java.util.Stack;

public class No226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>() {{
            add(root);
        }};

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

        return root;
    }
}
