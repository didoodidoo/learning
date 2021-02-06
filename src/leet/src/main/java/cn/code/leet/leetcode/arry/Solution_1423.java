package cn.code.leet.leetcode.arry;

public class Solution_1423 {

    public int maxScore(int[] cardPoints, int k) {
        // 求一个len-k的窗口的最小值就行了
        int minVal = Integer.MAX_VALUE;
        int len = cardPoints.length;
        int sum = getSum(cardPoints);
        if (k >= len)
            return sum;
        int windowLen = len - k;
        int left = 0;
        int right = windowLen - 1;
        int windowSum = 0;
        for (int i = 0; i < windowLen; i++) {
            windowSum += cardPoints[i];
        }

        minVal = Math.min(minVal, windowSum);
//      开滑
        while (right < len - 1) {
            windowSum = windowSum + cardPoints[++right] - cardPoints[left++];
            minVal = Math.min(minVal, windowSum);
        }
        return sum - minVal;
    }

    public int getSum(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }
}
