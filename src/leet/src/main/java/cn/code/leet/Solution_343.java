package cn.code.leet;

public class Solution_343 {
    public int integerBreak(int n) {
//      一个数可以分成两个数 对应两个数的最大乘积
//        要注意 两个数的最大乘积可能没有这两个数的乘积大
//        0和1都不能拆 这两个0
        int[] result = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
//              拆成两个数的和 有可能会再拆
//                不再拆的情况下两数相乘  再拆的情况下
                max = Math.max(max, Math.max(j * (i - j), j * result[i - j]));
            }
            result[i] = max;
        }
        return result[n];
    }
}
