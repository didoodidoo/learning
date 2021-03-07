package cn.code.leet.leetcode.compettion;

public class Solution_0307 {

    //只含有一个连续1组成的字段 我日他大爷
//    public boolean checkOnesSegment(String s) {
//        if(s.length()==0)
//            return false;
//        if(s.length()==1)
//            return true;
//        boolean has = false;
//        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '1') {
//                count++;
////                后一个是不是1
//                if (i<s.length()-1&&s.charAt(i + 1) == '1') {
//                    if (!has) {
////                      如果到现在还没有连续的1  那么这就是连续的1
//                        count++;
//                        has = true;
//                    } else {
//                        if (s.charAt(i - 1) != '1') {
//                            return false;//有两个连续1
//                        }
//                    }
//                }
//            }
//        }
//        return has||count==1;
//    }

    public boolean checkOnesSegment(String s) {
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)=='1'&&s.charAt(i-1)=='0')
                return false;
        }
        return true;
    }

    // 目标 先算总和再取模emmm。。。 101000
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for(int num:nums){
            sum+=num;
        }
        System.out.println(sum);
        long sub = Math.abs((long)goal-sum);
        long r = sub/limit;
        if(sub%limit==0)
            return (int)r;
        else
            return (int)(r+1);
    }


}
