package top100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class No94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;

        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }

        return res;
    }


    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        while (root != null) {
            //没有左子树，直接访问该节点，再访问右子树
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                //有左子树，找前驱节点，判断是第一次访问还是第二次访问
                TreeNode pre = root.left;
                while (pre.right != null && pre.right != root)
                    pre = pre.right;
                //是第一次访问，访问左子树
                if (pre.right == null) {
                    pre.right = root;
                    root = root.left;
                }
                //第二次访问了，那么应当消除链接
                //该节点访问完了，接下来应该访问其右子树
                else {
                    pre.right = null;
                    ans.add(root.val);
                    root = root.right;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        No94_InorderTraversal solution = new No94_InorderTraversal();
        System.out.println(solution.inorderTraversal2(root));
    }
}
