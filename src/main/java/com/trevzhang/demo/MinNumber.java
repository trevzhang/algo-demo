package com.trevzhang.demo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 把数组排成最小的数 - 排序实现（最优实现）
 *
 * @author trevor
 * @since 2023/11/15 18:01
 **/


public class MinNumber {

    public static void main(String[] args) {
        // 示例测试
        MinNumber minNumber = new MinNumber();

        int[] example1 = {11, 3};
        System.out.println(minNumber.minNumber(example1)); // 输出 "113"

        int[] example2 = {};
        System.out.println(minNumber.minNumber(example2)); // 输出 ""

        int[] example3 = {3, 32, 321};
        System.out.println(minNumber.minNumber(example3)); // 输出 "321323"

        int[] example4 = {0, 6, 2, 7, 1996, 4, 3, 32, 321};
        System.out.println(minNumber.minNumber(example4)); // 输出 "019962321323467"
    }

    public String minNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }

        // 将数组中的数字转换为字符串
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        // 自定义比较规则
        Arrays.sort(strNumbers, new Comparator<String>() {
            public int compare(String s1, String s2) {
                // 拼接时比较大小
                String order1 = s1 + s2;
                String order2 = s2 + s1;
                return order1.compareTo(order2);
            }
        });

        // 拼接结果
        StringBuilder result = new StringBuilder();
        for (String str : strNumbers) {
            result.append(str);
        }

        return result.toString();
    }
}


