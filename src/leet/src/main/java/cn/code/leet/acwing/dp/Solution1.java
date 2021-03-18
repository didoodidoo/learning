package cn.code.leet.acwing.dp;

// 01背包
/*
    dp[i][j] 只看前i个物品，总体积是j的情况下，总价值最大是多少
    不选第i个物品 dp[i-1][j-1]
    //选这个物品 前i个体积是j-这个物品的体积
    选第i个物品 dp[i-1][j-v[i]]
* */
public class Solution1 {

    //等于是两个背包
//    public int findMaxForm(String[] strs, int m, int n) {
//        //选自己和不选自己两种状态
//        //不选自己的话就是dp[i-1]
//        //要怎么把三维数组压成二维数组
//        int[][] cost = getCount(strs);
//        int[][][] dp = new int[m][n][strs.length];
//
//
//
//    }

    public int[][] getCount(String[] strs) {
        int[][] result = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            int count1 = 0;
            int count0 = 0;
            String s = strs[i];
            for (char c : s.toCharArray()) {
                if (c == '1') count1++;
                else count0++;
                result[i][0] = count0;
                result[i][1] = count1;
            }
        }
        return result;
    }

}
