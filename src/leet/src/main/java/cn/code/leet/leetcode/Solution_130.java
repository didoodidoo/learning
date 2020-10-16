package cn.code.leet.leetcode;

public class Solution_130 {
    public void solve(char[][] board) {
        for (int m = 1; m < board.length - 1; m++) {
            for (int n = 1; n < board[0].length - 1; n++) {
                if (board[m][n] == 'O')
                    around(board, m, n);
            }
        }
        for (int m = 1; m < board.length - 1; m++) {
            for (int n = 1; n < board[0].length - 1; n++) {
                if (board[m][n] == 'O')
                    board[m][n] = 'A';
            }
        }
    }

    public boolean around(char[][] board, int m, int n) {
        if (m == 0 || n == 0 || m == board.length - 1 || n == board[0].length - 1)
            return false;
//        变成A说明已经遍历过了
        board[m][n] = 'A';
        boolean top = board[m - 1][n] == 'X' || board[m - 1][n] == 'A' || around(board, m - 1, n);
        boolean button = board[m + 1][n] == 'X' || board[m + 1][n] == 'A' || around(board, m + 1, n);
        boolean left = board[m][n - 1] == 'X' || board[m][n - 1] == 'A' || around(board, m, n - 1);
        boolean right = board[m][n + 1] == 'X' || board[m][n + 1] == 'A' || around(board, m, n + 1);
        if (top && button && left && right) {
            board[m][n] = 'X';
            return true;
        }
        return false;
    }
}
