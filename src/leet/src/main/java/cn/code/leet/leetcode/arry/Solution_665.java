package cn.code.leet.leetcode.arry;

/*
 * 665. 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * 说明：
 *
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class Solution_665 {

    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        if (len <= 2)
            return true;
        int left = 0;
        int right = 1;
        boolean changed = false;
//        如果是递减了，就把他变成和left一样就没影响了
        while (right < len) {
            if (nums[right] < nums[left]) {
                if (changed) {
                    return false;
                } else {
//                    看下支不支持把左边变成偏小的，不支持就把右边变成左边
                    changed = true;
                    if (checkLeft(nums, left, right))
                        nums[left] = nums[right];
                    else
                        nums[right] = nums[left];
                }
            }
            left++;
            right++;
        }
        return true;
    }

    private boolean checkLeft(int[] nums, int left, int right) {
        if (left == 0)
            return true;
        return nums[left - 1] <= nums[right];
    }
}
