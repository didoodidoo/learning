package cn.code.leet.leetcode.dp;


import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

/**
 * 动态规划 凑零钱
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class Solution_322 {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0)
            return 0;
        int[] dp = new int[amount + 1];
        // 需要先填充，不然会导致初值是0
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount + 1;
            for (int c : coins) {
                if (i - c < 0) continue;
//                因为这里如果相等的话会 i==c 会变成 1+0
                dp[i] = Math.min(dp[i], 1 + dp[i - c]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    //    上面是求最少 这个是求总数
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount + 1; x++) {
                dp[x] += dp[x - coin];
            }
        }
        ArrayUtil.printArr(dp);
        return dp[amount];

    }

    @Test
    public void test() {
        change(5, new int[]{1, 2, 5});
    }

}
