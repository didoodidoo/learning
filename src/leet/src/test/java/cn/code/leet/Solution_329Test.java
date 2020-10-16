package cn.code.leet;

import cn.code.leet.leetcode.Solution_329;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_329Test {

    private Solution_329 solution = new Solution_329();

    @Test
    void longestIncreasingPath() {
        int[][] arr = new int[3][3];
        arr[0] = new int[]{9, 9, 4};
        arr[1] = new int[]{6, 6, 8};
        arr[2] = new int[]{2, 1, 1};
        assertEquals(4,solution.longestIncreasingPath(arr));
    }
}