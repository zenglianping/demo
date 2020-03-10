package com.example.demo.datastructure;

public class SortDemo {

    public static void main(String[] args) {

    }

    private void quickSort(int[] arr) {
        int len = arr.length;
        int left =0, right=len-1 , key = arr[len/2] ;
        while (left < right) {
            for (int i = right ; i >= left; i--) {
                if (arr[i] > key) {
                    right--;
                    break;
                }
            }

            for (int i = left; i < right ; i++ ) {
                if (arr[i] < key) {
                    left++;
                    break;
                }
            }

            if (left != right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }


    }
}
