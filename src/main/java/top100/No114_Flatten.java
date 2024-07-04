package top100;

import java.util.ArrayList;

public class No114_Flatten {
    public void flatten(TreeNode root) {
        ArrayList<TreeNode> res = new ArrayList<>();

        add2List(root, res);

        for (int i = 1; i < res.size(); i++) {
            TreeNode node = res.get(i - 1);
            node.left = null;
            node.right = res.get(i);
        }
    }

    private void add2List(TreeNode root, ArrayList<TreeNode> res) {
        if (root == null) return;

        res.add(root);
        add2List(root.left, res);
        add2List(root.right, res);
    }
}
