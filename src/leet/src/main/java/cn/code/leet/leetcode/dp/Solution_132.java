package cn.code.leet.leetcode.dp;

import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Solution_132 {

    public int minCut(String s) {
        int min = Integer.MAX_VALUE;
        //表示从i到j的是不是回文串
        int[][] dp = new int[s.length()][s.length()];
        //初始化
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        //构造出所有回文子串
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i <= 2) dp[i][j] = 1;
                    else if (dp[i + 1][j - 1] == 1) dp[i][j] = 1;
                }
            }
        }
        int[] dp2 = new int[s.length()];
        Arrays.fill(dp2, s.length());
        for (int i = 0; i < s.length(); i++) {
            if (dp[0][i] == 1) {
                dp2[i] = 0;
            } else {
                for (int j = 1; j <= i; j++) {
                    if (dp[j][i] == 1) {
                        dp2[i] = Math.min(dp2[i], dp2[j-1] + 1);
                    }
                }
            }
        }
        ArrayUtil.printArr(dp2);
        return dp2[s.length() - 1];
    }


    @Test
    public void test(){
        minCut("aabcaa");
    }

}
