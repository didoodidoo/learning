package cn.code.leet.leetcode.linklist;

import cn.code.leet.structure.ListNode;

//链表成环检测
public class Solution_142 {

    public ListNode detectCycle(ListNode head) {
        //链表成环可以用快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            //已经尾节点了
            if (fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
//                相遇说明有环  这个时候要找出环起点
                ListNode node = head;
                while (node != slow) {
                    node = node.next;
                    slow = slow.next;
                }
                return node;
            }
        }
        return null;
    }
}
