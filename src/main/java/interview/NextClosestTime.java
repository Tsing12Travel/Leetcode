package interview;

import java.util.HashSet;
import java.util.Set;

// 2024.11.14 百度大模型数据架构研发工程师
public class NextClosestTime {
    public String nextClosestTime(String time) {
        // 提取给定时间中的数字
        Set<Character> allowedDigits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c != ':') allowedDigits.add(c);
        }

        // 提取当前小时和分钟
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));

        while (true) {
            // 增加时间一分钟
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }

            if (hours == 24) {
                hours = 0;
            }

            // 生成新的小时和分钟字符串
            String newTime = String.format("%02d:%02d", hours, minutes);

            // 检查生成的新时间是否符合条件
            boolean isValid = true;
            for (char c : newTime.toCharArray()) {
                if (c != ':' && !allowedDigits.contains(c)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) return newTime;
        }
    }


    public static void main(String[] args) {
        NextClosestTime solution = new NextClosestTime();
        // String result = solution.nextClosestTime("12:34");
        // String result = solution.nextClosestTime("23:59");
        String result = solution.nextClosestTime("09:09");  // 下一个时间是 00:00 ?
        System.out.println(result);  // 输出应为 "12:41"
    }
}

