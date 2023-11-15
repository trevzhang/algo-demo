package com.trevzhang.demo;


import java.util.ArrayList;
import java.util.List;

/**
 * 把数组排成最小的数 - 回溯实现
 *
 * @author trevor
 * @since 2023/11/15 18:05
 **/
public class MinNumberBacktracking {

    public String minNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }

        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[numbers.length];
        backtrack(numbers, used, new StringBuilder(), result);

        return result.isEmpty() ? "" : result.get(0);
    }

    private void backtrack(int[] numbers, boolean[] used, StringBuilder current, List<String> result) {
        if (current.length() == numbers.length) {
            if (result.isEmpty() || current.toString().compareTo(result.get(0)) < 0) {
                result.clear();
                result.add(current.toString());
            }
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(numbers[i]);

                backtrack(numbers, used, current, result);

                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        MinNumberBacktracking minNumberBacktracking = new MinNumberBacktracking();

        int[] example1 = {11, 3};
        System.out.println(minNumberBacktracking.minNumber(example1)); // 输出 "113"

        int[] example2 = {};
        System.out.println(minNumberBacktracking.minNumber(example2)); // 输出 ""

        int[] example3 = {3, 32, 321};
        System.out.println(minNumberBacktracking.minNumber(example3)); // 输出 "321323"
    }
}
