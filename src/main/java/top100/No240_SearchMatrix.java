package top100;

public class No240_SearchMatrix {
    // 暴力解法
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == target) return true;
            }
        }

        return false;
    }


    /* 将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。
    因此，通过从 “根节点” 开始搜索，遇到比 target 大的元素就向左，反之向右，即可找到目标值 target

    “根节点” 对应的是矩阵的 “左下角” 和 “右上角” 元素，暂称之为 标志数 ，以 matrix 中的 左下角元素 为标志数 flag ，则有:
        若 flag > target ，则 target 一定在 flag 所在 行的上方 ，即 flag 所在行可被消去。
        若 flag < target ，则 target 一定在 flag 所在 列的右方 ，即 flag 所在列可被消去。
    */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;

        while (i >= 0 && j < matrix[i].length) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}};
        No240_SearchMatrix solution = new No240_SearchMatrix();
        System.out.println(solution.searchMatrix2(matrix, 20));
    }
}
