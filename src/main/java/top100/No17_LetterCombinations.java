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


    public static void main(String[] args) {
        String digits = "23";
        No17_LetterCombinations no17 = new No17_LetterCombinations();
        List<String> res = no17.letterCombinations(digits);
        System.out.println(res);
    }
}
