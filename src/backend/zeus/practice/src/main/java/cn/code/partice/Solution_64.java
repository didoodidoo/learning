package cn.code.partice;

import static cn.code.partice.util.ArrayUtil.printArr;

public class Solution_64 {
    public static void main(String[] args) {
        int[][] arr = new int[2][3];
        arr[0] = new int[]{1, 2, 5};
        arr[1] = new int[]{3, 2, 1};
//        arr[2] = new int[]{4, 2, 1};
        printArr(arr);
        minPathSum(arr);
    }


    public static int minPathSum(int[][] grid) {

//        想要求最短的路径 只要求出上边的和左边的最小的然后加起来就行
//        以此类推 一直取到最上面
        //行数
        int i = grid.length;
        //列数
        int j = grid[0].length;
        int[][] path = new int[i][j];
        path[0][0] = grid[0][0];
        //初始化第一行
        for (int m = 1; m < j; m++) {
            path[0][m] = grid[0][m] + path[0][m - 1];
        }
        //初始化第一列
        for (int m = 1; m < i; m++) {
            path[m][0] = grid[m][0] + path[m - 1][0];
        }

        for (int m = 1; m < i; m++) {
            for (int n = 1; n < j; n++) {
                path[m][n] = Math.min(path[m][n - 1], path[m - 1][n]) + grid[m][n];
            }
        }
        printArr(path);
        return path[i - 1][j - 1];
    }

}
