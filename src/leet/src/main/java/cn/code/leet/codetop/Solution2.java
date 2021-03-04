package cn.code.leet.codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

    //无重叠区间 先排序
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1)
            return 0;
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int count = 0;
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < right) {
                count++;
                right = Math.min(right, intervals[i][1]);
            }else{
                right =  intervals[i][1];
            }
        }
        return count;
    }

    //合并重叠区间
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][2];
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{intervals[0][0],intervals[0][1]});
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1;i<intervals.length;i++){
            if(intervals[i][0]<=right){
                //重叠了 把right换成较大的 ，left不动
                right = Math.max(intervals[i][1],right);
                list.set(list.size()-1,new int[]{left,right});
            }else{
                left = intervals[i][0];
                right = intervals[i][1];
                list.add(new int[]{left,right});
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
