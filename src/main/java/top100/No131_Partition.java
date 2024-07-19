package top100;

import java.util.ArrayList;
import java.util.List;

public class No131_Partition {
    private final List<List<String>> res = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;

    // 输入的视角（逗号选或不选）
    // 假设每对相邻字符之间有个逗号，那么就看每个逗号是选还是不选。也可以理解成：是否要把 s[i] 当成分割出的子串的最后一个字符。
    public List<List<String>> partition(String s) {
        this.s = s;
        if (s.isEmpty()) return res;

        dfs(0, 0);
        return res;
    }

    public boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    // start 表示当前这段回文子串的开始位置
    private void dfs(int i, int start) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));  // 复制 path
            return;
        }

        // 不选 i 和 i + 1 之间的逗号（i = n - 1 时一定要选）
        if (i < s.length() - 1) {
            dfs(i + 1, start);
        }

        // 选 i 和 i + 1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1);  // 下一个子串从 i + 1 开始
            path.removeLast();  // 恢复现场
        }
    }


    public static void main(String[] args) {
        String str = "aab";
        // String str = "a";
        No131_Partition sol = new No131_Partition();
        System.out.println(sol.partition(str));
    }
}
