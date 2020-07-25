package cn.code.leet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_410Test {

    private Solution_410 solution = new Solution_410();

    @Test
    void splitArray() {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        assertEquals(18, solution.splitArray(nums, m));
    }

    @Test
    void splitArrayTest2() {
        int[] nums = {1,2,3,4,5};
        int m = 2;
        assertEquals(9, solution.splitArray(nums, m));
    }
}