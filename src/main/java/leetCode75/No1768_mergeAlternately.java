package leetCode75;

/*
1768. 交替合并字符串
给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
返回合并后的字符串 。
*/
public class No1768_mergeAlternately {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        StringBuilder res = new StringBuilder(len1 + len2);
        int x = 0, y = 0;

        // 交替处理字符
        while (x < len1 && y < len2) {
            res.append(word1.charAt(x++));
            res.append(word2.charAt(y++));
        }

        // 追加剩余字符
        if (x < len1) res.append(word1.substring(x));
        if (y < len2) res.append(word2.substring(y));

        return res.toString();
    }


    public String mergeAlternately2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int idx1 = 0, idx2 = 0;
        StringBuilder sb = new StringBuilder();

        while (idx1 < len1 && idx2 < len2) {
            sb.append(word1.charAt(idx1++));
            sb.append(word2.charAt(idx2++));
        }

        while (idx1 < len1) {
            sb.append(word1.charAt(idx1++));
        }

        while (idx2 < len2) {
            sb.append(word2.charAt(idx2++));
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        No1768_mergeAlternately No1768 = new No1768_mergeAlternately();

        // 测试用例
        String word1 = "abc";
        String word2 = "pqr";
        System.out.println(No1768.mergeAlternately(word1, word2)); // 输出：apbqcr

        word1 = "ab";
        word2 = "pqrs";
        System.out.println(No1768.mergeAlternately(word1, word2)); // 输出：apbqrs

        word1 = "abcd";
        word2 = "pq";
        System.out.println(No1768.mergeAlternately(word1, word2)); // 输出：apbqcd
    }
}
