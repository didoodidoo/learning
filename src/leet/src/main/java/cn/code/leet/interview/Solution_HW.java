package cn.code.leet.interview;

import java.util.*;

public class Solution_HW {

    public String solution(String s) {
        StringBuffer result = new StringBuffer();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c))
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);
        }
        List<Map.Entry<Character, Integer>> nums = new ArrayList<>(map.entrySet());
        nums.sort((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Character, Integer> n : nums) {
            for (int i = 0; i < n.getValue(); i++) {
                result.append(n.getKey());
            }
        }
        return result.toString();
    }
}

