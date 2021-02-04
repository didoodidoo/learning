package cn.code.leet.leetcode;

/*给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
示例：
输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
提示：
1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。
*/
public class Solution_643 {

    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        int left = 0;
        int right = k - 1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        int maxSum = sum;

        while (right < len - 1) {
            int numRight = nums[++right];
            int numLeft = nums[++left];
            sum = sum+numRight-numLeft;
            maxSum = Math.max(maxSum,sum);
        }

        return maxSum/(k*1.0);
    }
}
