package cn.code.leet.util;

import java.util.Stack;

public class MyQueue {

    Stack<Integer> s1,s2;


    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
//            把一个栈的数据放到另外一个栈
        if(s1.isEmpty()&&s2.isEmpty()){
            s1.push(x);
            return ;
        }
        if(s1.isEmpty()){
            while(!s2.isEmpty()) s1.push(s2.pop());
            s1.push(x);
            while(!s1.isEmpty()) s2.push(s1.pop());
        }else{
            while(!s1.isEmpty()) s2.push(s1.pop());
            s2.push(x);
            while(!s2.isEmpty()) s1.push(s2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!s1.isEmpty())
            return s1.pop();
        if(!s2.isEmpty())
            return s2.pop();
        return -1;
    }

    /** Get the front element. */
    public int peek() {
        if(!s1.isEmpty())
            return s1.peek();
        if(s2.isEmpty())
            return s2.peek();
        return -1;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty()&&s2.isEmpty();
    }

}