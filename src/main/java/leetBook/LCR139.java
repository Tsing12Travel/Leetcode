package leetBook;

//教练使用整数数组 actions 记录一系列核心肌群训练项目编号。为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。请将调整后的训练项目编号以 数组 形式返回。

//解题思路：
//        考虑定义双指针 i , j 分列数组左右两端，循环执行：
//
//指针 i 从左向右寻找偶数；
//指针 j 从右向左寻找奇数；
//将 偶数 actions[i] 和 奇数 actions[j] 交换。
//可始终保证： 指针 i 左边都是奇数，指针 j 右边都是偶数 。

public class LCR139 {
    public int[] trainingPlan(int[] actions) {
        int left = 0, right = actions.length - 1, temp;

        while (left < right) {
            while (left < right && (actions[left] & 1) == 1) {
                left++;
            }

            while (left < right && (actions[right] & 1) == 0) {
                right--;
            }

            temp = actions[left];
            actions[left] = actions[right];
            actions[right] = temp;
        }

        return actions;
    }
}
