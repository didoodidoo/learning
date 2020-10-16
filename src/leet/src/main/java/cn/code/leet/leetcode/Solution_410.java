package cn.code.leet.leetcode;


public class Solution_410 {
    public int splitArray(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(num, left);
            right += num;
        }
//        用二分法逐渐逼近极限
//        子数组和的最大值
        while (left < right) {
            int mid = (right - left) / 2 + left;
//            开始分割数组
            int sum = 0;
            int count = 0;
            for (int num : nums) {
//                和比最大值大了 说明前一个数组已经是子数组的极限了
                if (sum + num > mid) {
//                    计数+1 重置和
                    count++;
                    sum = num;
                } else {
                    sum += num;
                }
            }
//            最后一个数组是没有算到的
            count++;
//          数量比要求少 说明这个预估值偏大了在左半边继续二分
//          写二分的时候要把所有条件都列出来便于理清思路
            if (count < m)
                right = mid - 1;
            else if (count == m)
                right = mid; //这个时候已经符合要求了 但是还得再找找有没有更小的
            else if (count > m)
                left = mid + 1;
        }
        return left;
    }
}
