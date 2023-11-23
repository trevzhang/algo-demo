package com.trevzhang.demo;

/**
 * @author trevor
 * @since 2023/11/23 12:26
 **/
public class CountSort {
    public static void main(String[] args) {
        // 定义字符串
        String str = "ajbchuegvcuekjcvgceuygceyge";
        // 将字符串转换为字符数组
        char[] charArray = str.toCharArray();
        // 定义一个长度为26的数组，用于统计每个字符出现的次数
        int[] countArray = new int[26];
        // 遍历字符数组，统计每个字符出现的次数
        for (char c : charArray) {
            countArray[c - 'a']++;
        }
        // 定义一个StringBuilder对象，用于存储排序后的字符串
        StringBuilder sortedStr = new StringBuilder();
        // 遍历计数数组，根据每个字符出现的次数将字符添加到排序后的字符串中
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedStr.append((char) ('a' + i));
            }
        }
        // 输出排序后的字符串
        System.out.println(sortedStr.toString());
    }
}
