package interview;

/*
n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

你需要按照以下要求，给这些孩子分发糖果：
每个孩子至少分配到 1 个糖果。
相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

示例 1：
输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。

示例 2：
输入：ratings = [1,2,2]
输出：4
解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
*/

/*
解题思路：
    规则定义：设学生 A 和学生 B 左右相邻，A 在 B 左边；
    左规则：当 ratingsB>ratingsA 时，B 的糖比 A 的糖数量多。
    右规则：当 ratingsA>ratingsB 时，A 的糖比 B 的糖数量多。
    相邻的学生中，评分高的学生必须获得更多的糖果 等价于所有学生满足左规则且满足右规则。

    算法流程：
    先从左至右遍历学生成绩 ratings，按照以下规则给糖，并记录在 left 中：
    先给所有学生 1颗糖；
    若 ratings[i]>ratings[i − 1]，则第 i 名学生糖比第 i − 1名学生多 1个。
    若 ratings[i]<=ratings[i − 1]，则第 i 名学生糖数量不变。（交由从右向左遍历时处理。）

    经过此规则分配后，可以保证所有学生糖数量满足左规则 。
    同理，在此规则下从右至左遍历学生成绩并记录在 right 中，可以保证所有学生糖数量 满足右规则 。
    最终，取以上 2轮遍历 left 和 right 对应学生糖果数的最大值，这样则 同时满足左规则和右规则，即得到每个同学的最少糖果数量。

    复杂度分析：
    时间复杂度 O(N) ：遍历两遍数组即可得到结果；
    空间复杂度 O(N) ：需要借用 left，right 的线性额外空间。
*/

import java.util.Arrays;

// 2024.11.07 广东省智能科学与技术研究院
public class No135_Candy {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            count += Math.max(left[i], right[i]);
        }

        return count;
    }


    public static void main(String[] args) {
        // int[] ratings = {1, 0, 2};
        int[] ratings = {1, 2, 2};
        No135_Candy candy = new No135_Candy();
        System.out.println(candy.candy(ratings));
    }
}
