package cn.code.leet.leetcode.dp;

/**
 * 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 */
public class Solution_300 {
    //那肯定是最长的+1喽
    //怎么判断是不是递增序列呢
//    在前置的数组中找到一个比它小的，把它的最大长度+1
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 1; i < dp.length; i++) {
//      如果一个也找不到，那就等于1
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
            System.out.println(max);
        }
        return max;
    }
}
