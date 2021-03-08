package cn.code.leet.leetcode.palindrome;

//最长回文子序列
public class Solution_516 {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int j = 0; j < s.length(); j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = 1;//不管怎么样 最小肯定是1
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2)
                        dp[i][j] = j - i + 1;
                    else
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    //一个数是不是回文数
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        String s = String.valueOf(x);
        for(int i = 0;i<s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length()-i-1))
                return false;
        }
        return true;
    }
}
