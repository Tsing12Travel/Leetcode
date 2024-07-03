package top100;

import java.util.Random;

public class No108_SortedArrayToBST {
    Random rand = new Random();

    public TreeNode sortedArrayToBST(int[] nums) {
        return rebuild(nums, 0, nums.length - 1);
    }

    private TreeNode rebuild(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;  // 总是选择中间位置左边的数字作为根节点
//        int mid = (left + right + 1) / 2;  // 总是选择中间位置右边的数字作为根节点
//        int mid = (left + right + rand.nextInt(2)) / 2;  // 选择任意一个中间位置数字作为根节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left = rebuild(nums, left, mid - 1);
        root.right = rebuild(nums, mid + 1, right);

        return root;
    }
}
