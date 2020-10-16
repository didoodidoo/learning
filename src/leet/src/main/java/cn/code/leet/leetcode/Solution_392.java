package cn.code.leet.leetcode;

public class Solution_392 {

    public boolean isSubsequence(String s, String t) {
//        两个指针 一个指s 一个指t 往后找就行
//        if(s.length()==1)
//            return t.contains(s);
        int sP = 0;
        int tP = 0;
        while (sP < s.length() && tP < t.length()) {
            boolean eq = false;
            while (tP < t.length()) {
                if (s.charAt(sP) == t.charAt(tP)) {
//                    找到了直接跳出 指针不用在这里后移了
                    eq = true;
                    break;
                }
                tP++;
            }
//            说明直到越界都没有找到
            if (!eq)
                return false;
//            找到了的话指针后移
            sP++;
            tP++;
        }
        return sP == s.length();
    }
}
