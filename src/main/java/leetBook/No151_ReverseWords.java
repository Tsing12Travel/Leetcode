package leetBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No151_ReverseWords {
    public static String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        String str = "test a is This World! Hello";
        System.out.println(reverseWords(str));
    }
}
