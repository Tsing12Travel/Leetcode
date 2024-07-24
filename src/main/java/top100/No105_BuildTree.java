package top100;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class No105_BuildTree {
    // 递归
    Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        indexMap = new HashMap<>();

        // 构造哈希映射，帮助我们快速定位根节点
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }

        return myBuildTree(preorder, inorder, 0, len - 1, 0, len - 1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) return null;

        // 前序遍历中的第一个节点就是根节点。可以不写，这里写出来只是为了提高可读性
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;

        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界 + 1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位 - 1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界 + 1 + 左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位 + 1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }


    // 递归
    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int root, int left, int right) {
        // 递归终止
        if (left > right) return null;

        // 建立根节点
        TreeNode node = new TreeNode(preorder[root]);
        // 划分根节点、左子树、右子树
        int i = dic.get(preorder[root]);

        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        // `root + i - left + 1` 含义为 `根节点索引 + 左子树长度 + 1`
        node.right = recur(root + i - left + 1, i + 1, right);  // 开启右子树递归
        return node;                                                      // 回溯返回根节点
    }


    // 迭代
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);

        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();

            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }

                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        No105_BuildTree tree = new No105_BuildTree();
        TreeNode root = tree.buildTree(preorder, inorder);
        System.out.println(root.val);
    }
}
