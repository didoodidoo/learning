package cn.code.leet.util;

public class ArrayUtil {


    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void printArr(int[][] arr) {
        int i = arr.length;
        int j = arr[0].length;
        System.out.println("***************************");
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                System.out.print(arr[m][n] + "  ");
            }
            System.out.println("");
        }
        System.out.println("***************************");
    }

    public static void printArr(int[] arr) {
        int i = arr.length;
        int j = arr.length;
        System.out.println("***************************");
        for (int m = 0; m < i; m++) {
            System.out.print(arr[m] + "  ");
            System.out.println("");
        }
        System.out.println("***************************");
    }
}
