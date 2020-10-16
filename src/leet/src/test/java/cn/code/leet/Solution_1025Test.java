package cn.code.leet;

import cn.code.leet.leetcode.Solution_1025;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_1025Test {

    private Solution_1025 solution = new Solution_1025();
    @Test
    void divisorGameTest1() {
        assertTrue(solution.divisorGame(22));
    }

    @Test
    void divisorGameTest2() {
        assertFalse(solution.divisorGame(1));
    }

    @Test
    void divisorGameTest3() {
        assertFalse(solution.divisorGame(3));
    }
}