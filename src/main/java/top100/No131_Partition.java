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
        // 这里的【选】指的是把 i 当作子串的右端点。因为 n - 1 一定是子串的右端点，所以一定要选。
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


    // 答案的视角（枚举子串结束位置）
    public List<List<String>> partition2(String s) {
        this.s = s;
        backtrack(0);
        return res;
    }

    private void backtrack(int curr) {
        if (curr == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = curr; i < s.length(); i++) {  // 枚举子串的结束位置
            if (isPalindrome(curr, i)) {
                path.add(s.substring(curr, i + 1));
                backtrack(curr + 1);
                path.removeLast();
            }
        }
    }


    public static void main(String[] args) {
        String str = "aab";
        // String str = "a";
        No131_Partition sol = new No131_Partition();
        System.out.println(sol.partition2(str));
    }
}
