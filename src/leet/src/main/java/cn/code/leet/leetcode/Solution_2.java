package cn.code.leet.leetcode;

import cn.code.leet.structure.ListNode;

public class Solution_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(l1.val + l2.val);
        ListNode tail = head;
        while (l1.next != null || l2.next != null) {
            int sum = 0;
            if (l1.next != null) {
                l1 = l1.next;
                sum += l1.val;
            }
            if (l2.next != null) {
                l2 = l2.next;
                sum += l2.val;
            }
            tail.next = new ListNode(sum);
            tail = tail.next;
        }
        parseList(head);
        return head;
    }

    private void parseList(ListNode list) {
//        记录低位是否要像高位进一
        ListNode p = list;
        boolean advance = false;

        while (p.next != null) {
            if (advance)
                p.val = p.val + 1;
            if (p.val >= 10) {
                p.val = p.val % 10;
                advance = true;
            } else {
                advance = false;
            }
            p = p.next;
        }
//  处理尾节点
        if (advance)
            p.val = p.val + 1;
        if (p.val >= 10) {
            p.val = p.val % 10;
            p.next = new ListNode(1);
        }
    }
}