package cn.code.leet;

public class Solution_1025 {
    public boolean divisorGame(int N) {
        //奇数先手必败 偶数先手必胜 用数学归纳法证明的
        //也可以用动态规划，弄一个bool[1000]的数组可以，但是又要遍历因数好难
        //[1] = false,[2] = true, 假设在(0,k)上结论成立
        //f(k) = true k为偶数 求证 f(k+1) = false
        //k+1为奇数 奇数只能被奇数整除  奇数减去一个奇数变成偶数 此时x在(0,k)之间结论成立 则f(k+1)为false
        //同理可证 k为奇数的情况下 f(k+1) 为true
        //则 先手偶数必胜  奇数必败
        return N % 2 == 0;
    }
}
