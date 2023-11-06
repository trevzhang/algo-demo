package com.trevzhang.demo;

import java.util.Arrays;

/**
 * 标题：数组均分
 * <p>
 * 题目描述
 * 一个整数数组，长度为n，将其分为m 份，使各份的和相等，求m 的最大值
 * 比如数组：{3，2，4，3，6}
 * m=1，可以分成{3，2，4，3，6} ;
 * m=2，可以分成{3,6}{2,4,3} ；
 * m=3，可以分成{3,3}{2,4}{6}
 * 所以m 的最大值为3
 *
 * @author kanda
 * @since 2023/11/06 23:55
 */
public class TestMaxGroup {

    /**
     * 测试程序入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 打印欢迎消息
        System.out.println("hello world");
        // 创建一个整数数组
        int[] arr = new int[]{3, 2, 4, 3, 6};
        // 调用maxGroup方法查找最大分组数
        int result = maxGroup(arr);
        // 打印结果
        System.out.println("最大分组数: " + result);
    }

    /**
     * 查找整数数组的最大分组数
     *
     * @param arr 整数数组
     * @return 最大分组数
     */
    private static int maxGroup(int[] arr) {
        int n = arr.length; // 获取数组的长度
        int sum = 0; // 用于存储数组元素的总和

        // 计算数组元素的总和
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        // 辅助数组，用于跟踪分组
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化辅助数组
            aux[i] = 0;
        }

        // 从n开始递减分组数m
        for (int m = n; m >= 2; m--) {
            // 如果总和不能被m整除，跳过当前分组数
            if (sum % m != 0) {
                continue;
            }

            // 如果找到了合适的分组，返回分组数m
            if (testGroup(arr, n, m, sum / m, aux, sum / m, 1)) {
                System.out.println(
                    "回溯完毕, m=" + m + ", aux=" + Arrays.toString(aux) + ", sum=" + sum
                        + ", sum/m=" + sum / m);
                return m;
            }
        }
        // 如果没有找到合适的分组，返回0
        return 0;
    }

    /**
     * 递归查找分组
     *
     * @param arr      数组
     * @param n        数组长度
     * @param m        分组数
     * @param groupSum 分组总和
     * @param aux      辅助数组
     * @param goal     待分配的目标总和
     * @param groupId  分组ID
     * @return 是否找到合适的分组
     */
    private static boolean testGroup(int[] arr, int n, int m, int groupSum, int[] aux, int goal, int groupId) {
        if (goal < 0) {
            // 如果目标总和小于0，返回false
            return false;
        }

        if (goal == 0) {
            System.out.println("find groupId " + groupId + ": " + printGroup(arr, aux, groupId));
            // 如果已经分配了所有分组，返回true
            if (groupId == m) {
                return true;
            }
            // 分组总和达到目标总和，切换到下一个分组
            groupId++;
            // 重置目标总和
            goal = groupSum;
        }

        for (int i = 0; i < n; i++) {
            // 如果元素已经被分配到分组，跳过
            if (aux[i] != 0) {
                continue;
            }
            // 将元素分配到当前分组
            aux[i] = groupId;

            if (testGroup(arr, n, m, groupSum, aux, goal - arr[i], groupId)) {
                // 如果找到合适的分组分配，返回true
                return true;
            }

            // 撤销分组分配
            aux[i] = 0;
        }

        // 未找到合适的分组分配，返回false
        return false;
    }

    private static String printGroup(int[] arr, int[] aux, int groupId) {
        // 检查输入参数是否有效
        if (arr == null || arr.length == 0 || aux == null || aux.length == 0) {
            return null;
        }

        // 创建一个StringBuilder来构建结果字符串
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // 遍历辅助数组aux，找到属于指定groupId的元素
        for (int i = 0; i < aux.length; i++) {
            if (aux[i] == groupId) {
                // 将属于groupId的元素添加到字符串中
                sb.append(arr[i]).append(",");
            }
        }
        // 删除最后一个多余的逗号
        sb.deleteCharAt(sb.length() - 1);
        // 添加分组结束符
        sb.append("]");
        // 返回构建的字符串表示该分组的元素
        return sb.toString();
    }
}
