package top100;

import java.util.*;

public class No114_Flatten {
    // 递归方式前序遍历
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


    // 迭代方式实现前序遍历
    public void flatten2(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                res.add(curr);
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();  // 注意这里弹出的节点为当前节点
            curr = curr.right;
        }

        for (int i = 1; i < res.size(); i++) {
            TreeNode node = res.get(i - 1);
            node.left = null;
            node.right = res.get(i);
        }
    }


    /* 思路：
    将左子树插入到右子树的地方
    将原来的右子树接到左子树的最右边节点
    考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
    */
    public void flatten3(TreeNode root) {
        while (root != null) {
            // 左子树为 null，直接考虑下一个点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找到左子树最右边的节点
                TreeNode prev = root.left;
                // 注意查找条件是 prev.right != null
                while (prev.right != null) {
                    prev = prev.right;
                }

                // 将原来的右子树接到左子树的最右边节点
                prev.right = root.right;
                // 将左子树插入到根节点右边(即右子树)
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }


    // 本质上和 flatten3 方法一致
    public void flatten3_2(TreeNode root) {
        if (root == null) return;  // 这里可以不写，当根节点为空时，可以不做任何操作

        while (root != null) {
            TreeNode node = root.left;
            if (node != null) {
                while (node.right != null) {
                    node = node.right;
                }

                node.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            } else {
                root = root.right;
            }
        }
    }


    private TreeNode prev = null;

    public void flatten4(TreeNode root) {
        if (root == null) return;

        flatten4(root.left);
        flatten4(root.right);
        root.left = null;
        root.right = prev;
        prev = root;
    }


    public void flatten5(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            if (pre != null) {
                pre.right = temp;
                pre.left = null;
            }

            if (temp.right != null) {
                s.push(temp.right);
            }

            if (temp.left != null) {
                s.push(temp.left);
            }

            pre = temp;
        }
    }
}
