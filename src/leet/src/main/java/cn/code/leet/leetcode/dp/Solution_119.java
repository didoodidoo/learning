package cn.code.leet.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//杨辉三角 返回第k行的值
//动态规划，只需要记上一行和这一行就行了
//用一个二维数组 或者直接用两个数组吧
public class Solution_119 {

    public List<Integer> getRow(int rowIndex) {

        List<Integer> last = new ArrayList<>();
        last.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> nums = new ArrayList<>(i + 1);
            nums.add(1);
            for (int j = 1; j < i; j++) {
                nums.add(last.get(j - 1) + last.get(j));
            }
            nums.add(1);
            last = nums;
        }
        return last;
    }

}
