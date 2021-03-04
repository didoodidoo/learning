package cn.code.leet.leetcode.dp;

import java.util.Arrays;

public class Solution_354 {

    public int maxEnvelopes(int[][] envelopes) {
        //先排序 然后往前找
        int[] dp = new int[envelopes.length];
        Arrays.sort(envelopes, ((o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            else
                return o1[0] - o2[0];
        }));
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            //往前找对应的位置 找不到就为1
            boolean find = false;
            for (int j = i; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    System.out.println("i:"+i+" j:"+j);
                    dp[i] = dp[j] + 1;
                    max = Math.max(dp[i], max);
                    find = true;
                    break;
                }
            }
            if (!find) dp[i] = 1;
        }
        return max;
    }
}
