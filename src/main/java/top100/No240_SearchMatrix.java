package top100;

public class No240_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) return true;
            }
        }

        return false;
    }

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
