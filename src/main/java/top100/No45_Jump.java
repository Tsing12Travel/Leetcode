package top100;

public class No45_Jump {
    // 如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数
    public int jump(int[] nums) {
        if (nums.length == 0) return 0;

        int end = 0;
        int maxPosition = 0;
        int step = 0;

        // 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。
        // 如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {  // 你到达你最远的地方，你就知道你下一步最远到哪
                end = maxPosition;
                step++;
            }
        }
        return step;
    }


    public static void main(String[] args) {
        No45_Jump jump = new No45_Jump();
        System.out.println(jump.jump(new int[]{2, 3, 0, 1, 4}));
    }
}
