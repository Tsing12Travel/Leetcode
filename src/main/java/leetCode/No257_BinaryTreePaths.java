package leetCode;

import java.util.ArrayList;
import java.util.List;

public class No257_BinaryTreePaths {
    // 递归，路径为参数
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode node, String path, List<String> res) {
        if (node == null) return;

        path += node.val;
        if (node.left == node.right) {  // 叶子节点
            res.add(path);
            return;
        }

        path += "->";
        dfs(node.left, path, res);
        dfs(node.right, path, res);
    }


    // 回溯，路径为外部变量
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs2(root, path, res);
        return res;
    }

    private void dfs2(TreeNode node, List<String> path, List<String> res) {
        if (node == null) return;

        path.add(String.valueOf(node.val));
        if (node.left == node.right) {  // 叶子节点
            res.add(String.join("->", path));
        } else {
            dfs2(node.left, path, res);
            dfs2(node.right, path, res);
        }
        path.removeLast();  // 恢复现场
    }


    public static void main(String[] args) {
        No257_BinaryTreePaths No257 = new No257_BinaryTreePaths();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<String> res = No257.binaryTreePaths2(root);
        System.out.println(res);
    }
}
