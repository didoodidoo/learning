package cn.code.leet.leetcode.stack;

import java.util.LinkedList;
import java.util.Stack;

public class Solution_331 {
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        if (nodes.length == 1 && "#".equals(nodes[0])) return true;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < nodes.length; i++) {
            String s = nodes[i];
            if ("#".equals(s)) {
                if (stack.isEmpty())
                    return false;
                while (!stack.isEmpty()&&"#".equals(stack.peek())) {
                    stack.pop();//弹一个#
                    stack.pop(); //弹根
                }
                if (!stack.isEmpty())
                    stack.push(s); //重新给他压一个#进来
            } else {
//                有两棵树了
                if (stack.isEmpty() && i != 0) return false;
                stack.push(s);
            }

        }
        return stack.isEmpty();
    }
}
