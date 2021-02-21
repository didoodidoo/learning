package cn.code.leet.leetcode.compettion;

import org.junit.jupiter.api.Test;

public class Solution_0221 {

    //交替合并字符串
    public String mergeAlternately(String word1, String word2) {
        char[] result = new char[word1.length() + word2.length()];
        for (int i = 0; i < word1.length() + word2.length(); i++) {
//            偶数且不超过1的长度就从1中取
            if (i % 2 == 0 && i / 2 < word1.length() && i / 2 < word2.length()) {
                result[i] = word1.charAt(i / 2);
            } else {
//                a不够长了 那么就直接从b中取
                if (i >= word1.length() * 2) {
                    result[i] = word2.charAt(i - word1.length());
                } else if (i >= word2.length() * 2) {
//                 b不够长了 直接从a里面取
                    result[i] = word1.charAt(i - word2.length());
                } else {
                    result[i] = word2.charAt(i / 2);
                }
            }

            System.out.println(new String(result) + "  " + i);
        }
        return new String(result);
    }


    @Test
    public void test() {
        mergeAlternately("123", "abcdef");
    }


    public int[] minOperations(String boxes) {
//        暴力遍历 dp不会
        int[] answers = new int[boxes.length()];
        for (int i = 0; i < answers.length; i++) {
            int sum = 0;
            for (int j = i + 1; j < answers.length; j++) {
                if (boxes.charAt(j) == '1') {
                    sum += j - i;
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                if (boxes.charAt(j) == '1') {
                    sum += i - j;
                }
            }

            answers[i] = sum;
        }
        return answers;
    }


    public int maximumScore(int[] nums, int[] multipliers) {
        int[][] dp = new int[multipliers.length + 1][multipliers.length + 1];
//        里面的是right 和 left
//        在他周围找 最大的
//        i表示左边移动了几个 j表示右边移动了几个 是从1开始的所以真实的位置需要-1
        int max = Integer.MIN_VALUE;
//        先把只移左边和只移右边算出来
        for (int i = 1; i < multipliers.length + 1; i++) {
            int pos = i - 1;
//            把只移动左边构造出来
            dp[0][i] = nums[pos] * multipliers[pos] + dp[0][i - 1];
//            把只移动右边构造出来
            dp[i][0] = nums[nums.length - i]* multipliers[pos] + dp[i - 1][0];
        }

//        把整个dp构造出来
        for(int i = 1;i < multipliers.length + 1;i++){
//            需要乘的位置
            int pos = i - 1;
//            i = 1 意思只移动一次右边的 剩下的都移动左边
            for(int j = 1;j<multipliers.length + 1;j++){

            }
        }
//        在它的周围找
//          M = i+nums.length-j+1 走了多少步
//        dp[i][j] = Math.max(dp[i-1][j]+nums[i]*multipliers[M],dp[i][j-1]+nums[j]*multipliers[M])

        return max;
    }


    public int sumMax(int[] nums, int left, int right, int[] multipliers, int start) {
//        如果相等了 那么就只能拿这个数来乘了
        if (left == right) {
            return nums[left] * multipliers[start];
        }
        if (start == multipliers.length - 1) {
            return Math.max(nums[left] * multipliers[start], nums[right] * multipliers[start]);
        }
//        子数组的最大和
        int subLeft = sumMax(nums, left + 1, right, multipliers, start + 1);
        int subRight = sumMax(nums, left, right - 1, multipliers, start + 1);
//      要加上自己
        return Math.max(nums[left] * multipliers[start] + subLeft, nums[right] * multipliers[start] + subRight);
    }


}
