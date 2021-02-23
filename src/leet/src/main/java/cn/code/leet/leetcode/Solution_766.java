package cn.code.leet.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Solution_766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        return true;
    }

    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        int i = 0;
        while (i < height.length) {

//            遇到比他矮的了 就要把前面的算了 算完了之后都弹出去 在接着放新的进来
//          先放第一个 放第一个的时候要计算左边的 但是左边没有 就当无事发生
            while (!stack.isEmpty() && height[i] < stack.peek()) {
//                遇到小的了要把前面的全都算一遍
//                淦 怎么算呢 依次弹出来？

            }
            stack.push(i);
            i++;
        }
        return sum;

    }

    @Test
    public void test() {
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

}
