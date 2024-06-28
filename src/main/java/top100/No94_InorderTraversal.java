package top100;

import java.util.ArrayList;
import java.util.List;

public class No94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        No94_InorderTraversal solution = new No94_InorderTraversal();
        System.out.println(solution.inorderTraversal(root));
    }
}
