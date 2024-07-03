package leetCode;

import java.util.ArrayList;
import java.util.List;

public class No1382_BalanceBST {
    List<Integer> inorderSeq = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        return rebuild(0, inorderSeq.size() - 1);
    }

    private void getInorder(TreeNode root) {
        if (root == null) return;
        if (root.left != null) getInorder(root.left);
        inorderSeq.add(root.val);
        if (root.right != null) getInorder(root.right);
    }

    private TreeNode rebuild(int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(inorderSeq.get(mid));

        node.left = rebuild(left, mid - 1);
        node.right = rebuild(mid + 1, right);

        return node;
    }
}
