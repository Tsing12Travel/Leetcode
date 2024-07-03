package top100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No102_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();  // 将一层的数存入
            int size = queue.size();  // 每层数字个数

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
        }

        return res;
    }


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder2(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) res.add(new ArrayList<>());

        res.get(level).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}
