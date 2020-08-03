package cn.code.leet;

public class Solution_415 {

    public String addStrings(String num1, String num2) {
        //之前有个倒序的字符串相加的题目 这个和那个差不多,但是这次是正序的
        // 从后往前来 如果进位就 +1
        //把字符串倒置过来
        int len1 = num1.length();
        int len2 = num2.length();
        int maxLen = Math.max(len1, len2);
        StringBuffer result = new StringBuffer();
        boolean advance = false;
        for (int i = 1; i <= maxLen; i++) {
            int sum = 0;
            if (advance)
                sum += 1;
            if (i <= len1)
                sum += Integer.parseInt(String.valueOf(num1.charAt(len1 - i)));//从末位开始相加
            if (i <= len2)
                sum += Integer.parseInt(String.valueOf(num2.charAt(len2 - i)));
            if (sum >= 10) {
                sum = sum % 10;
                advance = true;
            } else {
                advance = false;
            }
            result.append(sum);
        }
        if(advance) result.append("1");
        return result.reverse().toString();
    }
}
