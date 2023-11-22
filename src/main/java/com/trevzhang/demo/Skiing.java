package com.trevzhang.demo;

/**
 * 多多在一个滑雪场滑雪,滑雪场由一个(n* n)二维数组表示,数组值为地面高度m。多多 可以选择从某一个位置开始滑,每次只能往上 下左右的某一个方向移动,同时只能从高处往 低处移动,多多希望滑动尽可能多的格子数。 输出最多可以滑动多少格(起始位置也算)
 * n <= 1000, m<1000
 * 要求:时间复杂度O(n^2)
 * 示例:
 * 输入:
 * [
 * [5, 4, 3],
 * [4, 1, 2], [3, 4, 2]
 * ]
 * 输出:5(5––>4–>3––>2->1)
 * 输入:
 * [5, 1, 4], [1, 2, 3], [2, 3, 2]
 * 1
 * 输出:4(4->3->2->1)
 *
 * @author trevor
 * @since 2023/11/22 21:13
 **/
public class Skiing {
    public static int getMaxLength(int[][] m) {
        int n = m.length;
        int[][] dp = new int[n][n];
        int maxPath = 0;

        // 初始化dp数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // 递归计算每个位置的最长滑雪路径长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(m, dp, i, j));
            }
        }

        return maxPath;
    }

    private static int dfs(int[][] m, int[][] dp, int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int n = m.length;
        int maxPath = 1;

        // 向上滑行
        if (i > 0 && m[i][j] > m[i - 1][j]) {
            maxPath = Math.max(maxPath, dfs(m, dp, i - 1, j) + 1);
        }

        // 向下滑行
        if (i < n - 1 && m[i][j] > m[i + 1][j]) {
            maxPath = Math.max(maxPath, dfs(m, dp, i + 1, j) + 1);
        }

        // 向左滑行
        if (j > 0 && m[i][j] > m[i][j - 1]) {
            maxPath = Math.max(maxPath, dfs(m, dp, i, j - 1) + 1);
        }

        // 向右滑行
        if (j < n - 1 && m[i][j] > m[i][j + 1]) {
            maxPath = Math.max(maxPath, dfs(m, dp, i, j + 1) + 1);
        }

        dp[i][j] = maxPath;
        return maxPath;
    }

    public static void main(String[] args) {
        int[][] m = {{5, 4, 3},
            {4, 1, 2},
            {3, 4, 2}};

        int maxLength = getMaxLength(m);
        System.out.println("最多可以滑动的格子数：" + maxLength);


        int[][] m2 = {
            {5, 1, 4},
            {1, 2, 3},
            {2, 3, 2}};

        int maxLength2 = getMaxLength(m2);
        System.out.println("最多可以滑动的格子数：" + maxLength2);
    }
}
