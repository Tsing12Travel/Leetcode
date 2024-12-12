package leetCode75;

public class No605_CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // 检查当前位置是否为空地，并且两侧没有种植花
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                count++;
                // 在当前位置种植一朵花
                flowerbed[i] = 1;
            }

            // 如果已经种够 n 朵花，直接返回 true
            if (count >= n) {
                return true;
            }
        }

        // 遍历结束后，检查是否种够了 n 朵花
        return count >= n;
    }


    public static void main(String[] args) {
        int[] flowerbed = new int[]{1, 0, 0, 0, 1};
        No605_CanPlaceFlowers No605 = new No605_CanPlaceFlowers();
        System.out.println(No605.canPlaceFlowers(flowerbed, 1));
        System.out.println(No605.canPlaceFlowers(flowerbed, 2));
    }
}
