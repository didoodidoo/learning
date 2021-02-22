package cn.code.leet.leetcode.dp;

//鸡蛋掉落
public class Solution_887 {
    public int superEggDrop(int K, int N) {
// 假如只有一层楼 那么只需要扔一次
// k个鸡蛋N层楼 找到最小的移动次数

//不管怎么样先那个数组

//        如果只有一个鸡蛋，那么需要移动的次数为N
//        F 1-N
        int dp[][] = new int[K + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
//           只有一个鸡蛋
            dp[1][i] = i;
        }


        return 0;
    }
}
