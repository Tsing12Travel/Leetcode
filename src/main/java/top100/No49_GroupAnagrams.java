package top100;

/* 49.字母异位词分组 */
// 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

import java.util.*;

public class No49_GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return null;
        }

        // 创建一个 HashMap 用于存储异位词分组的结果，键为排序后的字符串，值为对应分组的字符串列表
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 对当前字符串进行排序，作为 HashMap 的键
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);

            // 如果 HashMap 中不存在以 key 为键的列表，则创建一个新列表并放入 HashMap 中
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // 将当前字符串添加到对应的列表中
            map.get(key).add(str);
        }

        // 将 HashMap 的值（即所有异位词分组）转换为 List 并返回
        return new ArrayList<>(map.values());
    }


    public static List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        res.addAll(map.values());
        return res;
    }


    public static List<List<String>> groupAnagrams3(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int len = str.length();
            for (int i = 0; i < len; i++) {
                counts[str.charAt(i) - 'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        String[] str = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = groupAnagrams(str);
        System.out.println(res);
    }
}
