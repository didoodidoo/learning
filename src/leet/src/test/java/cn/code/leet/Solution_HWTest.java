package cn.code.leet;

import cn.code.leet.interview.Solution_HW;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_HWTest {

    private Solution_HW solution = new Solution_HW();

    @Test
    void solution() {
        assertEquals("eert",solution.solution("tree"));
    }

    @Test
    void solution_allSame() {
        assertEquals("aaaccc",solution.solution("cccaaa"));
    }


    @Test
    void solution_moreChar() {
        assertEquals("bbAa",solution.solution("Aabb"));
    }

}