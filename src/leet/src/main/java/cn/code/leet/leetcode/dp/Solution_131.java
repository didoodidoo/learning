package cn.code.leet.leetcode.dp;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_131 {
    List<List<String>> result = new ArrayList<>();
    int[][] dp;
    Deque<String> queue = new LinkedList<>();

    public List<List<String>> partition(String s) {
        //表示从i到j的是不是回文串
        dp = new int[s.length()][s.length()];
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

        // System.out.println(dp[0][3]);
        //回溯全排 需要所有的都是回文 那么只需要从第一个开始
        getStr(0, s);
        return result;
    }

    public void getStr(int start, String s) {
        if (start >= s.length()) {
            //说明已经到头了，确实是一种排列
            result.add(new ArrayList<>(queue));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (dp[start][i] == 1) {
                //说明这个是符合条件的 接着往下走 走完后把自己弹出来进下一轮
                queue.offer(s.substring(start, i + 1));
                getStr(i + 1, s);
                queue.pollLast();//把最后的那一个拿走
            }
        }
    }
}
