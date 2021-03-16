package cn.code.leet.base;

import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

public class QuickSort {

    public void quickSort(int[] nums) {
        quick_sort(nums, 0, nums.length-1);
    }

    public void quick_sort(int[] nums, int left, int right) {
        //左边的数都小于等于x，右边的数都大于等于
        if (left >= right)
            return;
        int num = nums[left];
//        如何把区间一分为二
        int i = left, j = right;
        while(i<j){
            while(nums[i]<num) i++;
            while(nums[j]>num) j--;
            if(i<j) swap(nums,i,j);
        }
        quick_sort(nums, left, j);
        quick_sort(nums, j+1, right);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test() {
        int[] nums = new int[]{6, 7, 9,18, 8,26,-1, 5, 2, 4, 3, 1};
        quickSort(nums);
        ArrayUtil.printArr(nums);
    }
}
