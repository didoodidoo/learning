package cn.code.leet.leetcode.graph;

/*
* 200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。



示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
示例 2：

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3


提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'*/
public class Solution_200 {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // System.out.println(i+" "+j);
                    change(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    public void change(int i, int j, char[][] grid) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0)
            return;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            //左
            change(i, j - 1, grid);
            //右
            change(i, j + 1, grid);
            //上
            change(i - 1, j, grid);
            //下
            change(i + 1, j, grid);
        }
    }
}
