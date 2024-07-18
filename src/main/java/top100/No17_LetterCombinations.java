package top100;

import java.util.ArrayList;
import java.util.List;

public class No17_LetterCombinations {
    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        int len = digits.length();
        if (len == 0) return res;

        char[] path = new char[len];  // 本题 path 长度固定为 len
        char[] digits_array = digits.toCharArray();
        dfs(digits_array, 0, path, res);
        return res;
    }

    private void dfs(char[] digits_array, int depth, char[] path, List<String> res) {
        if (depth == digits_array.length) {
            res.add(new String(path));
            return;
        }

        for (char c : MAPPING[digits_array[depth] - '0'].toCharArray()) {
            path[depth] = c;  // 直接覆盖
            dfs(digits_array, depth + 1, path, res);
        }
    }


    public List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        int len = digits.length();
        if (len == 0) return res;

        StringBuilder path = new StringBuilder();
        backtrack(digits, 0, path, res);
        return res;
    }

    private void backtrack(String digits, int depth, StringBuilder path, List<String> res) {
        if (depth == digits.length()) {
            res.add(path.toString());  // 注意此处 path 无需新建，即加入的时候不需要 new 新的列表。有些题目需要新建 path，否则最后会被回溯到空列表
            return;
        }

        char x = digits.charAt(depth);
        String str = MAPPING[x - '0'];
        for (int i = 0; i < str.length(); i++) {
            path.append(str.charAt(i));
            backtrack(digits, i + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        /* 也可写成如下循环
        for (char c : MAPPING[digits.charAt(depth) - '0'].toCharArray()) {
            path.append(c);
            backtrack(digits, depth + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }*/
    }


    // backtrack 不传递 depth 的版本(会比较抽象)
    public List<String> letterCombinations3(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;

        StringBuilder path = new StringBuilder();
        backtrack2(digits, path, res);
        return res;
    }

    private void backtrack2(String digits, StringBuilder path, List<String> res) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            return;
        }

        for (char c : MAPPING[digits.charAt(path.length()) - '0'].toCharArray()) {
            path.append(c);
            backtrack2(digits, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
         String digits = "23";
//        String digits = "2";
        No17_LetterCombinations no17 = new No17_LetterCombinations();
        List<String> res = no17.letterCombinations(digits);
        System.out.println(res);
    }
}
