package cn.code.leet.leetcode.arry;

//翻转矩阵
public class Solution_867 {

    public int[][] transpose(int[][] matrix) {
//        行列可能不相等 那就必须要新的数组了
        int[][] result = new int[matrix[0].length][matrix.length];
        for(int i = 0;i<result.length;i++){
            for(int j = 0;j<result[i].length;j++){
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
}
