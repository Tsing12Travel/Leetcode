package leetBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No151_ReverseWords {
    public static String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        String str = "test a is This World! Hello";
        System.out.println(reverseWords(str));
    }
}
