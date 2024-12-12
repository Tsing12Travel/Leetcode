package leetCode75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No151_ReverseWords {
    public String reverseWords(String s) {
        // List<String> list = new ArrayList<>(List.of(s.trim().split("\\s+")));
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }


    public String reverseWords2(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') left++;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') right--;

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {  // 单词之间只保留一个空格
                sb.append(c);
            }

            left++;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') end++;

            // 翻转单词
            reverse(sb, start, end - 1);

            // 更新 start，去找下一个单词
            start = end + 1;
            end++;
        }
    }


    public static String reverseWords3(String s) {
        // 去除首尾空格并按空格分割单词
        String[] words = s.trim().split("\\s+");

        // 使用 StringBuilder 来构建结果
        StringBuilder reversed = new StringBuilder();

        // 倒序遍历单词数组
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" "); // 添加单个空格
            }
        }

        return reversed.toString();
    }


    public static void main(String[] args) {
        No151_ReverseWords no151 = new No151_ReverseWords();
        String s = "Let's Go";
        System.out.println(no151.reverseWords(s));
    }
}
