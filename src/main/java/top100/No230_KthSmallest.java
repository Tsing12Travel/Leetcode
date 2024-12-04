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


    private int count = 0;  // 用于记录当前遍历到第几个元素
    private int result = 0;  // 保存结果

    public int kthSmallest3(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return result;
    }

    // 中序遍历辅助方法
    private void inOrderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        // 遍历左子树
        inOrderTraversal(node.left, k);

        // 访问当前节点
        count++;
        if (count == k) {
            result = node.val;
            return;  // 找到第 k 小的元素后提前结束
        }

        // 遍历右子树
        inOrderTraversal(node.right, k);
    }


    // 测试
    public static void main(String[] args) {
        // 示例树结构
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        No230_KthSmallest No230 = new No230_KthSmallest();
        int k = 2;  // 查找第 2 小的元素
        System.out.println("第 " + k + " 小的元素是: " + No230.kthSmallest(root, k));
    }
}
