package cn.code.leet.leetcode.compettion;

import org.junit.jupiter.api.Test;

public class Solution_220 {

    /**
     * 只包含大小写字母
     */
    public String longestNiceSubstring(String s) {
//        找到只出现一次的字符 用这个字符将字符串分割成两个子字符串 取最大
        if (s.length() < 2)
            return "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String an;
//            a 97 A 65 相差32
            if (c < 97)
//            说明是大写
                an = String.valueOf(c).toLowerCase();
            else
                an = String.valueOf(c).toUpperCase();
            if (!s.contains(an))
                return max(longestNiceSubstring(s.substring(0, i)), longestNiceSubstring(s.substring(i + 1)));
        }
        return s;
    }

    public String max(String s1, String s2) {
        if (s1.length() == s2.length())
            return s1;
        return s1.length() > s2.length() ? s1 : s2;
    }


    @Test
    public void test() {
        char a = 'a';
        char A = 'A';
        System.out.println(a + 0);
        System.out.println(A + 0);
    }
}
