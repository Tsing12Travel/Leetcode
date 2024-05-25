package doublePointer;

/*
344.反转字符串
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
*/

public class No344_ReverseString {
    public void reverseString(char[] s) {
        // 一左一右两个指针相向而行
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            // 交换 s[left] 和 s[right]
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right++;
        }
    }
}
