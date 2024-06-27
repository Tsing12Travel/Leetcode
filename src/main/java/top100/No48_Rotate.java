package top100;

import java.util.Arrays;

public class No48_Rotate {
    /*矩阵顺时针旋转 90º 后，可找到以下规律：
        「第 i 行」元素旋转到「第 n−1−i 列」元素；
        「第 j 列」元素旋转到「第 j 行」元素；
    因此，对于矩阵任意第 i 行、第 j 列元素 matrix[i][j] ，矩阵旋转 90º 后「元素位置旋转公式」为：
        matrix[i][j] → matrix[j][n−1−i]
        原索引位置 → 旋转后索引位置*/
    public void rotate(int[][] matrix) {
        int row_len = matrix.length;
        int col_len = matrix[0].length;

        // 深拷贝
        int[][] temp = new int[row_len][];
        for (int i = 0; i < row_len; i++) {
            temp[i] = matrix[i].clone();
        }

        // 根据旋转公式，遍历修改原矩阵 matrix 的各元素
        for (int i = 0; i < row_len; i++) {
            for (int j = 0; j < col_len; j++) {
                matrix[j][row_len - i - 1] = temp[i][j];
            }
        }
    }

    // 先矩阵转置，再左右对称的两列互换
    public void rotate1(int[][] matrix) {
        int len = matrix.length;
        int temp;

        // 矩阵转置
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 左右翻转矩阵
        for (int j = 0; j < len / 2; j++) {
            for (int i = 0; i < len; i++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = temp;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        No48_Rotate test = new No48_Rotate();
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        test.rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
