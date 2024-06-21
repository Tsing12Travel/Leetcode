package leetBook;

import java.util.ArrayList;
import java.util.List;

// 文件组合：待传输文件被切分成多个部分，按照原排列顺序，每部分文件编号均为一个 正整数（至少含有两个文件）。
// 传输要求为：连续文件编号总和为接收方指定数字 target 的所有文件。请返回所有符合该要求的文件传输组合列表。

/*
注意，返回时需遵循以下规则：
每种组合按照文件编号 升序 排列；
不同组合按照第一个文件编号 升序 排列。
*/

public class FileCombination {
    public int[][] fileCombination(int target) {
        int left = 1;
        double right = 2.0;
        List<int[]> res = new ArrayList<>();

        while (left < right) {
            right = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) left * left - left))) / 2;
            if (left < right && right == (int) right) {
                int[] temp = new int[(int) right - left + 1];
                for (int i = left; i <= (int) right; i++) {
                    temp[i - left] = i;
                }
                res.add(temp);
            }
            left++;
        }
        return res.toArray(new int[res.size()][]);
    }

    // 滑动窗口
    public int[][] fileCombination2(int target) {
        int left = 1;
        int right = 2;
        int sum = 3;
        List<int[]> res = new ArrayList<>();

        while (left < right) {
            if (sum == target) {
                int[] temp = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    temp[i - left] = i;
                }
                res.add(temp);

                sum = sum - left;
                left++;  // sum == target 时，记录区间值后，窗口向后滑动
            }else if (sum > target) {
                sum = sum - left;  // 注意 sum 的值。sum 和 left++ 的位置不能更换，否则 sum 的值会被比真实值小 1(因为 sum 应该减去 左边界右移前的值)
                left++;
            } else {
                right++;
                sum = sum + right;
            }
        }
        return res.toArray(new int[0][]);
    }
}
