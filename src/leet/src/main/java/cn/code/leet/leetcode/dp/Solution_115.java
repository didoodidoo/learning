package cn.code.leet.leetcode.dp;

import java.util.Arrays;

/*给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）

题目数据保证答案符合 32 位带符号整数范围。

*/
public class Solution_115 {


    public int numDistinct(String s, String t) {
        //s是主字符，t是匹配的
        // t的前i个字符 与 s的前j个字符怎么匹配
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        //第一行全是1
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= t.length(); i++) {
            char tc = t.charAt(i - 1);
            for (int j = 1; j <= s.length(); j++) {
                char sc = s.charAt(j - 1);
                if (sc == tc) dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[t.length()][s.length()];
    }
}