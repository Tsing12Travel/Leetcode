package leetCode75;

/*
443. 压缩字符串
给你一个字符数组 chars ，请使用下述算法压缩：
从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
如果这一组长度为 1 ，则将字符追加到 s 中。
否则，需要向 s 追加字符，后跟这一组的长度。
压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
请在 修改完输入数组后 ，返回该数组的新长度。
你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
*/
public class No443_Compress {
    /*要实现字符串数组的就地压缩，我们可以使用双指针方法，一个指针遍历原数组，另一个指针用于记录压缩后的数组位置。
    解题思路
    使用双指针:
        一个指针 i 遍历输入数组 chars。
        另一个指针 write 记录压缩后字符写入的位置。
    记录每组连续字符:
        从当前字符 chars[i] 开始，统计其连续出现的次数 count。
        如果 count > 1，将其长度以字符形式写入 chars。
    更新数组:
        将每组的字符和对应的长度写入 chars 数组，覆盖原有内容。
        当遍历完成时，write 指针指向的就是压缩后数组的长度。
    返回压缩后数组的长度:
        输出 write 即为压缩后的字符数组长度。*/
    public int compress(char[] chars) {
        int read = 0;  // 读取位置
        int write = 0;  // 写入位置

        while (read < chars.length) {
            char currChar = chars[read];
            int count = 0;

            // 统计当前字符的连续出现次数
            while (read < chars.length && chars[read] == currChar) {
                read++;
                count++;
            }

            // 写入字符
            chars[write++] = currChar;

            // 如果 count > 1，则写入计数
            if (count > 1) {
                for (char x : String.valueOf(count).toCharArray()) {
                    chars[write++] = x;
                }
            }
        }

        return write;
    }



    public static void main(String[] args) {
        No443_Compress No443 = new No443_Compress();

        // 测试用例
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int newLength = No443.compress(chars);

        // 打印压缩后的结果
        System.out.println("Compressed Length: " + newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(chars[i]);
        }
    }
}
