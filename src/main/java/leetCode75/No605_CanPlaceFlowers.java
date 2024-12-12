package leetCode75;

/*
605.种花问题
假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
*/

public class No605_CanPlaceFlowers {
    /*
    逻辑判断:
    检查当前位置 flowerbed[i] 是否为 0，且其左侧和右侧的位置（如果存在）是否也为空。
    如果符合条件，将当前位置设置为 1 表示种花，同时计数加一。

    优化:
    如果已经种够了 n 朵花，可以提前返回 true，避免不必要的遍历。

    边界处理:
    在判断左侧和右侧是否为空时，考虑到了边界情况，例如当前是第一个位置或者最后一个位置。
    */
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
