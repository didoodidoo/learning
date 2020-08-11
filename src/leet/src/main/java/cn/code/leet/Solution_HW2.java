package cn.code.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_HW2 {
//    a+b+c = 0

    public List<List<Integer>> solution(int[] nums) {
        if (nums.length < 3)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        int p1 = 0;
        int len = nums.length;
        Arrays.sort(nums);
        for (; p1 < len - 2; p1++) {
            int p2 = p1 + 1;
            if (nums[p1] + nums[p2] > 0)
                return result;
            for (; p2 < len - 1; p2++) {
                int p3 = p2 + 1;
//                偏小的三个数相加大于0之后后面的也不可能在出现等于0
                if (nums[p1] + nums[p2] + nums[p3] > 0)
                    return result;
                for (; p3 < len; p3++) {
                    if (nums[p1] + nums[p2] + nums[p3] == 0) {
                        result.add(Arrays.asList(nums[p1], nums[p2], nums[p3]));
                    }
                }
            }
        }
        return result;

    }
}
