package top100;

import java.util.Arrays;

public class No169_MajorityElement {
    // 摩尔投票。本题该方法的时间复杂度为 O(N)，空间复杂度为 O(1)
    /* 算法思路：
        候选人选择：
        维护一个候选元素 candidate 和计数器 count。
        遍历数组：
        如果 count 为 0，更新当前元素为候选元素，并将 count 置为 1。
        如果当前元素等于 candidate，则增加 count。
        否则，减少 count。
        多数元素确认：
        因为题目保证多数元素一定存在，所以最终的 candidate 就是数组的多数元素。*/
    public int majorityElement(int[] nums) {
        int vote = 0;
        int candidate = 0;

        for (int num : nums) {
            if (vote == 0) candidate = num;

            // 下面的代码可以写成 vote += num == candidate ? 1 : -1;
            if (num == candidate) {
                vote++;
            } else {
                vote--;
            }
        }
        return candidate;
    }


    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length / 2;
        return nums[mid];
    }


    public int majorityElement3(int[] nums) {  // 需要考虑不存在众数的写法
        int x = 0, votes = 0, count = 0;

        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }

        // 验证 x 是否为众数
        for (int num : nums)
            if (num == x) count++;

        return count > nums.length / 2 ? x : 0;  // 当无众数时返回 0
    }


    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        No169_MajorityElement No169 = new No169_MajorityElement();
        System.out.println(No169.majorityElement(nums));
    }
}
