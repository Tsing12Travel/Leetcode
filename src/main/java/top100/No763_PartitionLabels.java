package top100;

import java.util.ArrayList;
import java.util.List;

public class No763_PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        char[] x = S.toCharArray();
        int[] last = new int[26];

        for (int i = 0; i < x.length; i++) {
            last[x[i] - 'a'] = i;  // 每个字母最后出现的下标
        }

        int start = 0;
        int end = 0;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < x.length; i++) {
            end = Math.max(end, last[x[i] - 'a']);  // 更新当前区间右端点的最大值
            if (i == end) {  // 当前区间合并完毕
                res.add(end - start + 1);  // 区间长度加入答案
                start = i + 1;  // 下一个区间的左端点
            }
        }

        return res;
    }


    public static void main(String[] args) {
        No763_PartitionLabels test = new No763_PartitionLabels();
        String str = "ababcbacadefegdehijhklij";
        System.out.println(test.partitionLabels(str));
    }
}
