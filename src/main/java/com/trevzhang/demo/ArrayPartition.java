package com.trevzhang.demo;

/**
 * 数组均分 - 字节面试题
 * 给定一个整数数组，长度为n，将其分为m份，使各份的和相等，求m的最大值
 * 示例：数组{3, 2, 4, 3, 6}，可以分成{3, 2, 4, 3, 6}、{3, 6}、{2, 4, 3}，所以m的最大值为3
 */
public class ArrayPartition {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 3, 6};
        int result = maxGroup(arr);
        System.out.println(result);
    }

    /**
     * 求解数组最大可均分的份数
     *
     * @param arr 数组
     * @return 最大份数
     */
    private static int maxGroup(int[] arr) {
        int n = arr.length;
        // 求数组元素和
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // 用于记录数组元素是否已被使用的辅助数组
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            aux[i] = 0;
        }

        // 从总份数 n 开始递减，直到 2
        for (int m = n; m >= 2; m--) {
            // 如果总和不是份数的整数倍，跳过
            if (sum % m != 0) {
                continue;
            }
            // 测试是否可以将数组分成 m 份，每份的和为 sum / m
            if (testGroup(arr, n, m, sum / m, aux, sum / m, 1)) {
                return m;
            }
        }
        return 0;
    }

    /**
     * 递归测试是否可以将数组分成 m 份，每份的和为 groupSum
     *
     * @param arr          数组
     * @param n            数组长度
     * @param m            总份数
     * @param groupSum     每份的和
     * @param usedGroupIds 辅助数组，记录元素是否已被使用
     * @param goal         当前正在组合的组的和
     * @param groupId      当前正在组合的组的编号
     * @return 是否成功组合
     */
    private static boolean testGroup(int[] arr, int n, int m, int groupSum, int[] usedGroupIds, int goal, int groupId) {
        // 如果当前组的和小于 0，表示无法组合成功
        if (goal < 0) {
            return false;
        }
        // 如果当前组的和为 0，表示成功组合了一组
        if (goal == 0) {
            groupId++;
            goal = groupSum;
            // 如果已经组合了 m 组，返回成功
            if (groupId == m + 1) {
                return true;
            }
        }
        // 尝试将数组元素加入当前组
        for (int i = 0; i < n; i++) {
            // 如果数组元素已被使用，跳过
            if (usedGroupIds[i] != 0) {
                continue;
            }
            // 将数组元素加入当前组
            usedGroupIds[i] = groupId;
            // 递归尝试下一个元素
            if (testGroup(arr, n, m, groupSum, usedGroupIds, goal - arr[i], groupId)) {
                return true;
            }
            // 回溯，撤销对数组元素的选择
            usedGroupIds[i] = 0;
        }
        // 无法组合成功
        return false;
    }
}
