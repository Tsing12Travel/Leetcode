package top100;

import java.util.HashSet;
import java.util.Set;

public class No73_SetZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes1(int[][] matrix) {  // 和上面的方法一样，只不过是使用 set 存储 0
        Set<Integer> row_zero = new HashSet<>();
        Set<Integer> col_zero = new HashSet<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    row_zero.add(i);
                    col_zero.add(j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (row_zero.contains(i) || col_zero.contains(j)) matrix[i][j] = 0;
            }
        }
    }

    // 用 matrix 第一行和第一列记录该行该列是否有 0，作为标志位。但是对于第一行和第一列要设置一个标志位，为了防止自己这一行(一列)也有 0 的情况
    public void setZeroes2(int[][] matrix) {
        int row_len = matrix.length;
        int col_len = matrix[0].length;
        boolean row0_zero = false;
        boolean col0_zero = false;

        // 第一行是否有 0
        for (int j = 0; j < col_len; j++) {
            if (matrix[0][j] == 0) {
                row0_zero = true;
                break;
            }
        }

        // 第一列是否有 0
        for (int i = 0; i < row_len; i++) {
            if (matrix[i][0] == 0) {
                col0_zero = true;
                break;
            }
        }

        // 把第一行和第一轮作为标志位
        for (int i = 1; i < row_len; i++) {
            for (int j = 1; j < col_len; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 置 0
        for (int i = 1; i < row_len; i++) {
            for (int j = 1; j < col_len; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row0_zero) {
            for (int j = 0; j < col_len; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col0_zero) {
            for (int i = 0; i < row_len; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
