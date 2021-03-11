package cn.code.leet.leetcode.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//简单计算器 stack 括号匹配计算
public class Solution_224 {

    public int calculate(String s) {
        if(s.startsWith("-"))
            s = "0"+s;
        //尝试将正序的表达式转换成逆波兰表达式
        List<String> s1 = transform(s);
        return doCalculate(s1);

    }

    public List<String> transform(String s) {
        List<String> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        // 是数字的话就直接放到result中
        // 是符号的话就一通操作然后放到result中
        int pos = 0;
        while (pos < s.length()) {
            //是数字还需要往后找
            char c = s.charAt(pos);
            //或者前一个是符号 说明这个是负号
            if (isNumber(s,pos)) {
                int end = pos+1;
                while (end < s.length() && isNumber(s.charAt(end))) {
                    end++;
                }
                result.add(s.substring(pos, end));
                pos = end-1;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.add(stack.pop().toString());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } else if (c == '+' || c == '-') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.add(stack.pop().toString());
                }
                stack.push(c);
            }
            pos++;
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop().toString());
        }
        System.out.println(result);
        return result;
    }

    public boolean isChar(char c) {
        return c == '+' || c == '-' || c == '(';
    }

    public boolean isNumber(String s, int pos) {
        char c = s.charAt(pos);
        if (isNumber(c))
            return true;
        if (pos == 0 && (c == '-' || c == '+')) {
            return true;
        }
        if ((c == '+' || c == '-') && isChar(s.charAt(pos - 1)))
            return true;

        return false;
    }

    public boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public int doCalculate(List<String> list) {
        //计算逆波兰表达式
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if ("+".equals(s)) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                int sum = num1 + num2;
                stack.push(String.valueOf(sum));
            } else if ("-".equals(s)) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                int sum = num2 - num1;
                stack.push(String.valueOf(sum));
            } else {
                stack.push(s);
            }
        }
        return Integer.valueOf(stack.peek());
    }


    @Test
    public void test() {
        System.out.println(calculate("-2 + 1"));
    }
}
