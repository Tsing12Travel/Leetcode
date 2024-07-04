package top100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No199_RightSideView {
    // 深度优先
    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (res.size() <= depth) res.add(root.val);  // 右序遍历，因此只需要保存每层深度的第一个值

        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }


    // 深度优先
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs2(root, 0, res);
        return res;
    }

    private void dfs2(TreeNode root, int depth, List<Integer> res) {
        if (root == null) return;
        if (depth == res.size()) res.add(root.val);  // 这个深度首次遇到

        dfs2(root.right, depth + 1, res);  // 先递归右子树，保证首次遇到的一定是最右边的节点
        dfs2(root.left, depth + 1, res);
    }


    // 广度优先
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                if (i == size - 1) res.add(node.val);  // i == size - 1 表示取每层的最后一个值
            }
        }

        return res;
    }
}
