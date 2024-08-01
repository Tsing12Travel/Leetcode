package top100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No230_KthSmallest {
    List<Integer> res = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        add2List(root);
        Collections.sort(res);
        return res.get(k - 1);
    }

    private void add2List(TreeNode root) {
        if (root == null) return;

        add2List(root.left);
        res.add(root.val);
        add2List(root.right);
    }


    // 在二叉搜索树中，任意子节点都满足“左子节点 < 根节点 < 右子节点”的规则。因此二叉搜索树具有一个重要性质：二叉搜索树的中序遍历为递增序列
    // 本题可被转化为求中序遍历的第 k 个节点
    int ans, k;

    public int kthSmallest2(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        if (k == 0) return;
        if (--k == 0) ans = root.val;
        dfs(root.right);
    }
}
