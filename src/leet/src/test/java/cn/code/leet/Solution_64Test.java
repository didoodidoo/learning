package cn.code.leet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_64Test {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private Solution_64 solution_64 = new Solution_64();
    @Test
    void minPathSumTest1() {
        int[][] arr = new int[2][3];
        arr[0] = new int[]{1, 2, 5};
        arr[1] = new int[]{3, 2, 1};
        assertEquals(6,solution_64.minPathSum(arr));
    }

    @Test
    void minPathSumTest2() {
        int[][] arr = new int[3][3];
        arr[0] = new int[]{1, 3, 1};
        arr[1] = new int[]{1, 5, 1};
        arr[2] = new int[]{4, 2, 1};
        assertEquals(7,solution_64.minPathSum(arr));
    }
}