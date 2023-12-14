package com.trevzhang.demo;

/**
 * 归并排序
 * @author trevor
 * @since 2023/12/14 9:58
 **/
public class MergeSorting {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i);
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int mid = (left + right) / 2;
            // 对左边数组进行递归
            mergeSort(arr, left, mid);
            // 对右边数组进行递归
            mergeSort(arr, mid + 1, right);
            // 合并
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // 临时数组
        int[] temp = new int[right - left + 1];
        // 左指针
        int i = left;
        // 右指针
        int j = mid + 1;
        // 临时数组指针
        int t = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= right) {
            // 左边的数小于右边的数
            if (arr[i] < arr[j]) {
                // 把左边的数放入临时数组中
                temp[t++] = arr[i++];
            } else {
                // 把右边的数放入临时数组中
                temp[t++] = arr[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        // 把右边剩余的数移入数组
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        // 把新数组中的数覆盖arr数组
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}
