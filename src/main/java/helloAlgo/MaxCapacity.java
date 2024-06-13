package helloAlgo;

/* 最大容量：贪心 */
// 输入一个数组 ht ，其中的每个元素代表一个垂直隔板的高度。数组中的任意两个隔板，以及它们之间的空间可以组成一个容器。
// 容器的容量等于高度和宽度的乘积（面积），其中高度由较短的隔板决定，宽度是两个隔板的数组索引之差。
// 请在数组中选择两个隔板，使得组成的容器的容量最大，返回最大容量。
public class MaxCapacity {
    public int maxCapacity(int[] ht) {
        // 初始化两个指针 left、right 分别在数组两端
        int left = 0;
        int right = ht.length - 1;
        // 初始最大容量为 0
        int res = 0;

        // 循环贪心选择，直至两指针相遇
        while (left < right) {
            int temp = Math.min(ht[right], ht[left]) * (right - left);
            res = Math.max(res, temp);

            // 短板向内移动
            if (ht[left] < ht[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}
