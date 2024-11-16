package leetBook;

public class SpiralArray {
    public int[] spiralArray(int[][] array) {
        if (array == null || array.length == 0) return new int[0];

        int left = 0, right = array[0].length - 1, top = 0, bottom = array.length - 1, curr = 0;
        int[] res = new int[(right + 1) * (bottom + 1)];
        while (true) {
            for (int i = left; i <= right; i++) res[curr++] = array[top][i];  // left to right
            if (++top > bottom) break;

            for (int i = top; i <= bottom; i++) res[curr++] = array[i][right];  // top to bottom
            if (--right < left) break;

            for (int i = right; i >= left; i--) res[curr++] = array[bottom][i];  // right to left
            if (--bottom < top) break;

            for (int i = bottom; i >= top; i--) res[curr++] = array[i][left];  // bottom to top
            if (++left > right) break;
        }
        return res;
    }
}
