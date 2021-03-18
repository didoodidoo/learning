package cn.code.leet.leetcode.arry;

public class Solution_59 {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        fill(1, 0, 0, matrix, 1);
        return matrix;

    }

    public void fill(int num, int i, int j, int[][] matrix, int direction) {
        //全都不能检查就结束
        if (i >= matrix.length || j >= matrix[0].length || i < 0 || j < 0 || matrix[i][j] != 0)
            return;
        matrix[i][j] = num;
        num++;
        //决定下一个的方向
        switch (direction) {
            case 1: {
                //方向向右 检查能否继续向右，不能则向下
                if (j + 1 < matrix[0].length && matrix[i][j + 1] == 0)
                    fill(num, i, j + 1, matrix, direction);
                else
                    fill(num, i + 1, j, matrix, 2);
                break;
            }
            case 2: {//检查是否继续向下 不能则向左
                if (i + 1 < matrix.length && matrix[i + 1][j] == 0)
                    fill(num, i + 1, j, matrix, direction);
                else
                    fill(num, i, j - 1, matrix, 3);
                break;
            }
            case 3: {//是否能继续向左 不能就向上
                if (j - 1 >= 0 && matrix[i][j - 1] == 0)
                    fill(num, i, j - 1, matrix, direction);
                else
                    fill(num, i - 1, j, matrix, 4);
                break;
            }
            case 4: { //能否向上 不能则向右
                if (i - 1 >= 0 && matrix[i - 1][j] == 0)
                    fill(num, i - 1, j, matrix, direction);
                else
                    fill(num, i, j + 1, matrix, 1);
                break;
            }
            default:
                break;
        }
    }
}
