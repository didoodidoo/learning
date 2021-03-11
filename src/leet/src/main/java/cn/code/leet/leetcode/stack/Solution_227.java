package cn.code.leet.leetcode.stack;

import java.util.Stack;

public class Solution_227 {

    //今天的计算器没有括号 但是有乘除
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char perSign = '+';
        int num = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                num = num * 10 + (c - '0');
            //是符号或者已经是最后一位了 最后一位
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                // System.out.println(num+" "+perSign);
                switch (perSign) {
                    case ('+'): {
                        stack.push(num);
                        break;
                    }
                    case ('-'): {
                        stack.push(-num);
                        break;
                    }
                    case ('*'): {
                        stack.push(stack.pop() * num);
                        break;
                    }
                    default: {
                        stack.push(stack.pop() / num);
                        break;
                    }
                }
                // System.out.println(stack);
                //最后一位的时候c会变成数字 但已经没什么了
                perSign = c;
                num = 0;
            }
        }
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

}
