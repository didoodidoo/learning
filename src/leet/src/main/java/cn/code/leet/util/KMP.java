package cn.code.leet.util;

public class KMP {
//    借助于有限状态机的KMP匹配

    int[][] dp;
    String part;

    public KMP(String part) {
        this.part = part;
//        构建筑有限状态机 遇到什么字符应该退回哪个状态

        int m = part.length();
//        // dp[状态][字符] = 下个状态
        dp = new int[m][256];
//        // base case
        dp[0][part.charAt(0)] = 1;
//        // 影子状态 X 初始为 0
        int X = 0;
        for (int i = 1; i < m; i++) {
            char c = part.charAt(i);
            for(int n = 0;n<256;n++){
                if(c==n)
                    dp[i][n] = i+1;
                else
                    dp[i][n] = dp[X][n];//退回影子状态
            }
//            更新影子状态
            X = dp[X][c];
        }
//        // 当前状态 j 从 1 开始
//        for (int j = 1; j < M; j++) {
//            for (int c = 0; c < 256; c++) {
//                if (part.charAt(j) == c)
//                    dp[j][c] = j + 1;//相等则状态往后推进
//                else
//                    dp[j][c] = dp[X][c];//退回影子状态
//            }
//            // 更新影子状态
//            X = dp[X][part.charAt(j)];
//        }
    }

    public int search(String txt) {
/*        int M = part.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 当前是状态 j，遇到字符 txt[i]，
            // part 应该转移到哪个状态？
            j = dp[j][txt.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
        */

//      长度 也是状态机最终状态
        int m = part.length();
        int n = txt.length();
//        状态
        int j = 0;
        for (int i = 0; i < n; i++) {

            char c = txt.charAt(i);
//          直接取状态就行了
            j = dp[j][c];
//            如果达到了最终状态则说明匹配到了
            if (j == m) return i - m + 1;

        }
        return -1;
    }

}
