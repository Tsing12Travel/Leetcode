package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class No56_Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];

            if (merged.isEmpty() || merged.getLast()[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], right);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
