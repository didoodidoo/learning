package cn.code.leet.leetcode.arry;

public class Solution_832 {
    public int[][] flipAndInvertImage(int[][] A) {
//        原地翻转异或 还是很简单的
        for (int i = 0; i < A.length; i++) {
            int p1 = 0;
            int p2 = A[i].length - 1;
            for (int j = 0; j < A[i].length / 2; j++) {
                int tmp = A[i][p1];
                A[i][p1] = A[i][p2]^1;
                A[i][p2] = tmp^1;
                p1++;
                p2--;
            }
            if(A[i].length%2==1){
                A[i][ A[i].length / 2]^=1;
            }
        }
        return A;
    }
}
