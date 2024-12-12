package leetCode75;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No345_ReverseVowels {
    public String reverseVowels(String s) {
        // 将字符串 s 改成数组，方便交换字符
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        while (left < right) {
            // 从左向右找元音字母。chars.length 也可写成 s.length()
            while (left < chars.length && !vowels.contains(chars[left])) left++;
            // 从右向左找元音字母
            while (right >= 0 && !vowels.contains(chars[right])) right--;

            // 双指针相遇，退出
            if (left >= right) break;

            // 注意执行交换后，左右指针需要向中间移动一个步长，否则将陷入死循环(两个内层 while 循环均为 false，此处的交换会一直执行)
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }

        return new String(chars);
    }


    public String reverseVowels2(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        while (left < right) {
            if (!vowels.contains(chars[left])) {
                left++;
            } else if (!vowels.contains(chars[right])) {
                right--;
            } else {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }

        return new String(chars);
    }


    public static void main(String[] args) {
        String s = "IceCreAm";
        No345_ReverseVowels No345 = new No345_ReverseVowels();
        System.out.println(No345.reverseVowels(s));
    }
}
