package leetCode;

import java.util.ArrayList;
import java.util.List;

public class No784_LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, new StringBuilder(), 0, result);
        return result;
    }

    // 选或不选
    private void backtrack(String s, StringBuilder path, int index, List<String> result) {
        // 终止条件
        if (index == s.length()) {
            result.add(path.toString());
            return;
        }

        /*  所有字符（包括数字）都在 backtrack(s, path, index + 1, result); 里递归，
            确保 基础递归逻辑独立于字母大小写处理。
            如果是字母，就额外进行一次递归（改变大小写），相当于「选或不选」的两条路径。
            回溯时统一 deleteCharAt(path.length() - 1)，保证状态正确。
        */
        // 先加上当前字符（无修改）
        char c = s.charAt(index);
        path.append(c);
        backtrack(s, path, index + 1, result); // 递归处理下一个字符
        path.deleteCharAt(path.length() - 1);  // 回溯

        // 如果是字母，则尝试大小写变换（额外的选择）
        if (Character.isLetter(c)) {
            path.append(Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c));
            backtrack(s, path, index + 1, result);
            path.deleteCharAt(path.length() - 1);  // 回溯
        }
    }


    public List<String> letterCasePermutation1(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        // 如果是字母，则尝试大小写
        if (Character.isLetter(chars[index])) {
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(chars, index + 1, result);

            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(chars, index + 1, result);
        } else {
            // 如果是数字，直接跳过
            backtrack(chars, index + 1, result);
        }
    }


    public List<String> letterCasePermutation2(String s) {
        List<String> result = new ArrayList<>();
        backtrack2(s, new StringBuilder(), 0, result);
        return result;
    }

    private void backtrack2(String s, StringBuilder path, int index, List<String> result) {
        // 终止条件：遍历完所有字符
        if (index == s.length()) {
            result.add(path.toString());
            return;
        }

        char c = s.charAt(index);
        if (Character.isLetter(c)) {
            // 处理小写
            path.append(Character.toLowerCase(c));
            backtrack2(s, path, index + 1, result);
            path.deleteCharAt(path.length() - 1); // 回溯

            // 处理大写
            path.append(Character.toUpperCase(c));
            backtrack2(s, path, index + 1, result);
            path.deleteCharAt(path.length() - 1); // 回溯
        } else {
            // 处理数字，直接加入
            path.append(c);
            backtrack2(s, path, index + 1, result);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }


    public static void main(String[] args) {
        No784_LetterCasePermutation No784 = new No784_LetterCasePermutation();
        System.out.println(No784.letterCasePermutation("a1b2"));
        // 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
    }
}
