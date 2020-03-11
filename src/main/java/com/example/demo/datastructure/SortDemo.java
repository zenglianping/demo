package com.example.demo.datastructure;

import java.util.Arrays;

public class SortDemo {

    public static void main(String[] args) {

        int[] arr = new int[]{6, 1, 2, 5, 4, 3, 9, 7, 10, 8};

        SortDemo sortDemo = new SortDemo();

        sortDemo.quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));

    }

    //从小到大排序
    private void quickSort(int[] arr, int start, int end) {
        if (start > end) { // 中断递归
            return;
        }
        int key = arr[start]; // 用于比较的关键字
        int left = start;
        int right = end;
        while (left < right) {
            //从右往左
            while (arr[right] >= key && left < right) {
                right--;
            }

            //从左往右
            while (arr[left] <= key && left < right) {
                left++;
            }

            if (left < right) { //交换位置
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        //基准位置与分隔位互换
        arr[start] = arr[left];
        arr[left] = key;

        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);

    }


}
