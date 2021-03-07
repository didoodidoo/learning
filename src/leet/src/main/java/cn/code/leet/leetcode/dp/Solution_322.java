package cn.code.leet.leetcode.dp;


import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        //凑成这么多需要的零钱的个数 如果刚好相等 那么就是1
        int[] dp = new int[amount + 1];
        // 需要先填充，不然会导致初值是0
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            //就算是用1块的 最大的也不会超过amount
            dp[i] = amount + 1;
            for (int c : coins) {
                if (i - c < 0) continue;
//                因为这里如果相等的话会 i==c 会变成 1+0 就是刚好取一枚就能凑出来
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
        String path = "E:\\code\\ci-new\\bk-ci-ee\\support-files\\sql";
        {
            File file = new File(path);        //获取其file对象
            File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
            for (File f : fs) {                    //遍历File[]数组
                if (!f.isDirectory())        //若非目录(即文件)，则打印
                    System.out.println("source "+ f.getName()+";");
            }

        }
    }
}