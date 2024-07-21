package top100;

import java.util.ArrayList;
import java.util.List;

/* 设要计算的二维数组是 c，计算方法如下：
每一排的第一个数和最后一个数都是 1，即 c[i][0] = c[i][i] = 1。
其余数字，等于左上方的数，加上正上方的数，即 c[i][j] = c[i−1][j−1] + c[i−1][j] */
public class No118_Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0) return res;
        res.add(List.of(1));  // 第一行

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);  // 每行的第一个数是 1

            for (int j = 1; j < i; j++) {
                row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }

            row.add(1);  // 每行的最后一个数是 1
            res.add(row);  // 每行的值添加到杨辉三角中
        }
        return res;
    }


    public static void main(String[] args) {
        int numRows = 5;
        No118_Generate sol = new No118_Generate();
        System.out.println(sol.generate(numRows));
    }
}
