package leetCode75;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class No334_IncreasingTripletTest {
    @Test
    void testIncreasingTriplet() {
        No334_IncreasingTriplet solution = new No334_IncreasingTriplet();

        // 测试用例 1：存在递增子序列
        int[] nums1 = {2, 1, 5, 0, 4, 6};
        boolean result1 = solution.increasingTriplet(nums1);
        System.out.println("Test Case 1 Result: " + result1); // 打印测试结果
        assertTrue(solution.increasingTriplet(nums1), "Test case 1 failed!");

        // 测试用例 2：不存在递增子序列
        int[] nums2 = {5, 4, 3, 2, 1};
        boolean result2 = solution.increasingTriplet(nums2);
        System.out.println("Test Case 1 Result: " + result2); // 打印测试结果
        assertFalse(solution.increasingTriplet(nums2), "Test case 2 failed!");

        // 测试用例 3：数组长度小于 3
        int[] nums3 = {1, 2};
        boolean result3 = solution.increasingTriplet(nums3);
        System.out.println("Test Case 1 Result: " + result3); // 打印测试结果
        assertFalse(solution.increasingTriplet(nums3), "Test case 3 failed!");

        // 测试用例 4：多个相等元素
        int[] nums4 = {1, 1, 1, 1};
        boolean result4 = solution.increasingTriplet(nums4);
        System.out.println("Test Case 1 Result: " + result4); // 打印测试结果
        assertFalse(solution.increasingTriplet(nums4), "Test case 4 failed!");

        // 测试用例 5：递增但不连续
        int[] nums5 = {1, 10, 2, 9, 3};
        boolean result5 = solution.increasingTriplet(nums5);
        System.out.println("Test Case 1 Result: " + result5); // 打印测试结果
        assertTrue(solution.increasingTriplet(nums5), "Test case 5 failed!");
    }
}
