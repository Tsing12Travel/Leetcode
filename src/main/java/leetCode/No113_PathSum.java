package leetCode;

import java.util.ArrayList;
import java.util.List;

public class No113_PathSum {
    List<List<Integer>> res;
    List<Integer> path;
    int targetSum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        this.targetSum = targetSum;

        dfs(0, root);
        return res;
    }

    private void dfs(int sum, TreeNode node) {
        if (node == null) return;

        sum += node.val;
        path.add(node.val);
        if (node.left == node.right) {  // 叶节点
            if (sum == targetSum) {
                res.add(new ArrayList<>(path));  // 这里要 new 一个新的保存 path 的对象！！！!
                // return;  回溯的话这里就不能 return 了，return 会导致 remove 不了
            }
        } else {
            dfs(sum, node.left);
            dfs(sum, node.right);
        }
        path.removeLast();  // 恢复现场
    }


    public static void main(String[] args) {
        No113_PathSum No113 = new No113_PathSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        List<List<Integer>> res = No113.pathSum(root, 22);
        System.out.println(res);
    }
}
