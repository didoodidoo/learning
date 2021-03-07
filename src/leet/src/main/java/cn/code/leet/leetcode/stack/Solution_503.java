package cn.code.leet.leetcode.stack;

import java.util.Arrays;

public class Solution_503 {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        //暴力写肯定能写出来啊 n2
        Arrays.fill(result, -1);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 1; j < nums.length; j++) {
                int pos = (i + j) % nums.length;
                if (nums[pos] > num) {
                    result[i] = nums[pos];
                    break;
                }
            }
        }
        return result;
    }
}
