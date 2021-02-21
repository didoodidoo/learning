package cn.code.leet.leetcode.arry;

import java.util.TreeMap;

/*给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
如果不存在满足条件的子数组，则返回 0 。
输入：nums = [8,2,4,7], limit = 4
输出：2
解释：所有子数组如下：
[8] 最大绝对差 |8-8| = 0 <= 4.
[8,2] 最大绝对差 |8-2| = 6 > 4.
[8,2,4] 最大绝对差 |8-2| = 6 > 4.
[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
[2] 最大绝对差 |2-2| = 0 <= 4.
[2,4] 最大绝对差 |2-4| = 2 <= 4.
[2,4,7] 最大绝对差 |2-7| = 5 > 4.
[4] 最大绝对差 |4-4| = 0 <= 4.
[4,7] 最大绝对差 |4-7| = 3 <= 4.
[7] 最大绝对差 |7-7| = 0 <= 4.
因此，满足题意的最长子数组的长度为 2 。
*/
public class Solution_1438 {

    public int longestSubarray(int[] nums, int limit) {
//        滑动窗口 求最大值最小值的差
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < nums.length) {
            int num = nums[right++];
            map.put(num, map.getOrDefault(num, 0) + 1);
//            最大的减最小的
            while (map.lastKey() - map.firstKey() > limit) {
//                移动左指针 直到不超过
                int nl = nums[left++];
                map.put(nl, map.get(nl) - 1);
                if (map.get(nl) <= 0)
                    map.remove(nl);
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
