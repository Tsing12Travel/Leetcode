package interview;

// 2024.11.26 平安集团大数据团队线下笔试
// https://blog.csdn.net/qq_44700578/article/details/138046458
public class PingAn {
    public static void pingan() {
        String a = "abc";    // 字符串常量池
        String b = "abc";
        String c = new String("abc");    // new 创建对象，堆和常量池中都会有该对象
        String c1 = new String("abc");
        String d = "ab" + "c";   // 常量与常量拼接，结果在常量池中。查找常量池中是否存在"abc"，如果存在 则让 d 直接引用
        String e = new String("ab") + new String("c");

        // a、b、c、c1、d、e 使用 equals 比较，由于字符串内容都相等，所以均会返回 true
        // 故此处重点使用 ==，观察是否为同一个对象
        System.out.println("a==b：" + (a == b));   // true
        System.out.println("c==c1：" + (c == c1)); // false
        System.out.println("a==c：" + (a == c));   // false
        System.out.println("a==d：" + (a == d));   // true
        System.out.println("c==d：" + (c == d));   // false

        System.out.println("a==e：" + (a == e));   // false
        System.out.println("c==e：" + (c == e));   // false
        System.out.println("d==e：" + (d == e));   // false


        String str1 = "ab";
        String str2 = "cd";
        String str3 = "ab" + "cd";
        String str4 = "abcd";
        System.out.println("str3==str4：" + (str3 == str4));  // true

        // 内部实现 String temp = (new StringBuilder()).append(str1).append("cd").toString();
        String str5 = str1 + "cd";
        System.out.println("str5==str4：" + (str5 == str4));  // false

        // 内部实现 String temp1 = (new StringBuilder()).append(str1).append(str2).toString();
        String str6 = str1 + str2;
        System.out.println("str6==str4：" + (str6 == str4));  // false

        str5 = str5.intern();  // 将 str5 放进常量池，并将引用赋给原来的 str5
        System.out.println("str5==str4：" + (str5 == str4));  // true
    }


    public static void main(String[] args) {
        PingAn test = new PingAn();
        test.pingan();
    }
}
