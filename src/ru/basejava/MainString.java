package ru.basejava;

import java.util.Arrays;

public class MainString {
    public static void main1(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
//        String result = "";
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str).append(", ");
        }
        System.out.println(sb);

        System.out.println();
        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1.equals(str2));
    }
    public static class Main
    {
        public static void reverse(int[] arr, int k) {
            if (k > arr.length) {
                return;
            }
            int tmp = arr[0];
            for(int i = 1; i < k; i++) {
                arr[i-1] = arr[k-i];
                arr[k-i] = tmp;
                tmp = arr[i];
                if(i >= k/2) {
                    break;
                }
            }
        }

        public static boolean isSorted(int[] arr) {
            int arrLen = arr.length;
            if (arrLen == 0 || arrLen == 1) return true;
            for (int i = 1; i < arrLen; i++) {
                if (arr[i-1] > arr[i]) {
                    return false;
                }
            }
            return true;
        }

        public static int getMaxNumIndex(int[] arr) {
            int max = arr.length-1;
            for(int i = arr.length-1; i >= 0; i--) {
                if(arr[i] > arr[max])
                    max = i;
            }
            return max;
        }

        public static int[] removeLastElement(int[] arr) {
            arr = Arrays.copyOf(arr, arr.length - 1);
            return arr;
        }

        public static void sort(int[] arr) {
            int k = 0;
            int[] arr1 = arr;
            int i = 0;
            while (!isSorted(arr1) || arr.length != 0){
                if(getMaxNumIndex(arr) == arr.length-1) {
                    arr1[arr.length-1] = arr[arr.length-1];
                    arr = removeLastElement(arr);
                }
                if (arr.length == 0) {
                    break;
                }
                k = getMaxNumIndex(arr) + 1;
                reverse(arr, k);
                k = arr.length;
                reverse(arr, k);
            }
        }

        public static void main(String[] args) {

            int[] arr = {5,3,7,3,1,8,2,6,2,13,19,2,2,2,2,2,1,1,1,3,4,1,4,5,6,5,4,1,5,7,8,5,3,4};
            System.out.println(Arrays.toString(arr));
            reverse(arr, 2);
            System.out.println(Arrays.toString(arr));
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }


}
