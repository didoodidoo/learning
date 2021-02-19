package cn.code.leet.leetcode.arry;

/*
* 1004. 最大连续1的个数 III
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。



示例 1：

输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：

输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。*/
public class Solution_1004 {

    public int longestOnes(int[] A, int K) {
        int result = 0;
        int left = 0, right = 0;
        int sum0 = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[right++];
            if (num == 0)
                sum0++;
            while (sum0 > K) {
                if (A[left++] == 0)
                    --sum0;
            }
            // 这个时候right 已经加过了，所以算长度不用+1
            result = Math.max(result, right - left);
        }
        return result;
    }
}
