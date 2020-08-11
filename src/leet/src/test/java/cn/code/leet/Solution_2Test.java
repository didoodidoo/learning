package cn.code.leet;

import cn.code.leet.structure.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution_2Test {

    private Solution_2 solution = new Solution_2();

    @Test
    void addTwoNumbers() {
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l1.next = l11;
        l11.next = l12;
        l2.next = l21;
        l21.next = l22;

        solution.addTwoNumbers(l1,l2);

    }
}