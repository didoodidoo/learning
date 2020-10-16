package cn.code.leet.leetcode;

import com.sun.istack.internal.NotNull;

import java.util.Stack;

public class Solution_20 {
    public boolean isValid(@NotNull String s) {
        // stack 一个一个放 遇到匹配的就弹出来，看看最后栈空不空
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(stack.empty()){
                stack.push(s.charAt(i));
                continue;
            }

            if(s.charAt(i)==stack.peek()+1||s.charAt(i)==stack.peek()+2)
                stack.pop();
            else
                stack.push(s.charAt(i));
        }
        return stack.empty();
    }
}
