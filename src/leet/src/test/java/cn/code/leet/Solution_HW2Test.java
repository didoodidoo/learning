package cn.code.leet;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Solution_HW2Test {

    private Solution_HW2 solution = new Solution_HW2();

    @Test
    void solution() {
        List<List<Integer>> r =  solution.solution(new int[]{-1, 0, 1,0,0});
        for(List<Integer> arr:r){
            System.out.println(arr);
        }
    }
}