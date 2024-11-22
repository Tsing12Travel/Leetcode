package top100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No108_SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return rebuild(nums, 0, nums.length - 1);
    }

    private TreeNode rebuild(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;  // 总是选择中间位置左边的数字作为根节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left = rebuild(nums, left, mid - 1);
        root.right = rebuild(nums, mid + 1, right);

        return root;
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    // 把 nums[left] 到 nums[right - 1] 转成平衡二叉搜索树
    private TreeNode dfs(int[] nums, int left, int right) {
        if (left >= right) { // 修正：避免无限递归
            return null;
        }
        int m = (left + right) >>> 1;
        return new TreeNode(nums[m], dfs(nums, left, m), dfs(nums, m + 1, right));
    }

    // 层序遍历打印二叉树
    public void levelOrderPrint(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                result.add("null");
                continue; // 不再将 null 的子节点加入队列
            }

            result.add(String.valueOf(current.val));
            queue.add(current.left);
            queue.add(current.right);
        }

        // 去掉尾部多余的 null
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        No108_SortedArrayToBST No108 = new No108_SortedArrayToBST();
        int[] nums = {-10, -3, 0, 5, 9};

        // 测试 sortedArrayToBST
        TreeNode root1 = No108.sortedArrayToBST(nums);
        System.out.print("Level-order traversal of the constructed BST (Method 1): ");
        No108.levelOrderPrint(root1);

        // 测试 sortedArrayToBST2
        TreeNode root2 = No108.sortedArrayToBST2(nums);
        System.out.print("Level-order traversal of the constructed BST (Method 2): ");
        No108.levelOrderPrint(root2);
    }
}
