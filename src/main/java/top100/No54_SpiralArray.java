package top100;

public class No54_SpiralArray {
    public int[] spiralArray(int[][] array) {
        if (array == null || array.length == 0) return new int[0];

        int left = 0, right = array.length - 1, top = 0, bottom = array[0].length - 1;
        int[] res = new int[(right + 1) * (bottom + 1)];
        int curr = 0;

        while (true) {
            for (int i = left; i <= right; i++) res[curr++] = array[top][i];  // left to right
            // 重新定义上边界，若上边界超出下边界，则跳出循环
            if (++top > bottom) break;

            for (int i = top; i <= bottom; i++) res[curr++] = array[i][right];  // top to bottom
            if (--right < left) break;

            for (int i = right; i >= left; i--) res[curr++] = array[bottom][i];  // right to left
            if (--bottom < top) break;

            for (int i = bottom; i >= top; i--) res[curr++] = array[i][left];  // bottom to top
            if (++left < right) break;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        No54_SpiralArray test = new No54_SpiralArray();
        int[] res = test.spiralArray(array);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}
