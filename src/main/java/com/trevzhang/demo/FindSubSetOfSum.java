package com.trevzhang.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集的和是否等于目标和
 *
 * @author trevor
 * @since 2023/11/20 12:58
 **/
public class FindSubSetOfSum {
    // 给定一个集合，有N个正整数（int32），
    // 判断（求解）是否存在一个子集，子集和等于X, 输出一个子集
    // 数据范围1 ：1 <= N <= 20,  1 <= X <= 10^9
    // 数据范围2: 1 <= N <= 5000, 1 <= X <= 10^4
    //
    // input1:
    // 5
    // 7 9 13 23 30
    // 36
    //
    // output1:
    // 13 23

    public static void main(String[] args) {
        // ArrayList<String> strings = new ArrayList<String>();
        // strings.add("Hello, World!");
        // strings.add("Welcome to online interview system of Acmcoder.");
        // strings.add("This system is running Java 8.");

        // for (String string : strings) {
        //	System.out.println(string);
        //}

        // int a, b;
        // Scanner in = new Scanner(System.in);
        // while(in.hasNextInt()) {
        //	a = in.nextInt();
        //	b = in.nextInt();
        //	System.out.printf("Your result is : %d\n", a + b);
        //}

        int[] nums = new int[]{7, 9, 13, 23, 30};
        int target = 36;

        List<Integer> result = findSubSet(nums, target);
        System.out.println(result);

        // if (result != null && result.size() > 0) {
        //     for (Integer res : result) {
        //         System.out.println(res);
        //     }
        // }
    }

    // dp[i][j] i当前第i个数， j目标和
    // dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i-1]]     -> if set[i-1]<=j
    // dp[i][j] = dp[i-1][j]                            -> if set[i-1]>j
    public static List<Integer> findSubSet(int[] set, int sum) {
        int n = set.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (set[i - 1] <= j) {
                    // 如果当前元素小于等于目标和，考虑不选当前或选择当前元素的情况
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                } else {
                    // set[i-1]>j
                    // 当前元素大于目标和，选上个元素的结果
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // System.out.println(JSONUtil.toJsonPrettyStr(dp));

        if (!dp[n][sum]) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int i = n;
        int j = sum;
        // 从数据的最后一个元素开始，构建结果集
        while (i > 0 && j > 0) {
            // 如果当前结果为true，且与上一个结果不同，则说明当前元素是子集的一部分
            if (dp[i][j] && dp[i][j] != dp[i - 1][j]) {
                result.add(set[i - 1]); // 添加元素
                j -= set[i - 1]; // 剩余目标和减去当前元素
            }
            i--;
        }
        return result;
    }
}

