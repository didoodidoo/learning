package cn.code.leet.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution_480 {


    public double[] medianSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        if (len - k < 0)
            return new double[0];

//      0说明相等 只有一个
        double[] result = new double[len - k + 1];
        //用来保存延迟数组
        List<Integer> sortList = new ArrayList<>();
        int left = 0;
        int right = k - 1;
//        初始化有序数组
        for (int i = 0; i < k; i++) {
            sortList.add(nums[i]);
        }
        sortList.sort((o1,o2)->{
            if(o1>o2)
                return 1;
            else
                return -1;
        });
        System.out.println(sortList);
//        开滑

        while (right < len-1) {
            result[left] = getMid(sortList);
            right++;
//          移除left,加入right
            System.out.println("移除左边" + nums[left]);
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i) == nums[left]) {
                    sortList.remove(i);
                    break;
                }
            }
            System.out.println("加入右边" + right + " " + nums[right]+"现在list是" + sortList);
//            System.out.println(sortList);
            if(sortList.size()==0){
                sortList.add(nums[right]);
            }else{
                for (int i = 0; i < sortList.size(); i++) {
                    if (sortList.get(i) > nums[right]) {
                        sortList.add(i, nums[right]);
                        break;
                    }
//                System.out.println("i 是"+ i+" size "+sortList.size());
                    if (i == sortList.size() - 1) {
                        sortList.add(nums[right]);
                        break;
                    }
                }
            }
            System.out.println("加入结束" + sortList);
            left++;
        }

        return result;
    }

    public double getMid(List<Integer> sortList) {
        if (sortList.size() % 2 == 0) {
//            偶数
            return (sortList.get(sortList.size() / 2).doubleValue() + sortList.get(sortList.size() / 2 + 1).doubleValue()) / 2;

        } else {
            return sortList.get(sortList.size() / 2 + 1).doubleValue();
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        medianSlidingWindow(nums, 3);
    }
}
