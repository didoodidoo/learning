/*
* 92. 反转链表 II
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
说明:
1 ≤ m ≤ n ≤ 链表长度。
示例:
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
* */
package cn.code.leet.leetcode.linklist;

import cn.code.leet.structure.ListNode;
import org.junit.jupiter.api.Test;

public class Solution_92 {

    //思路很简单，等于是便利了一个额外的链表 然后把指针指一下

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;
        //翻转那部分的尾节点
        ListNode newTail = head;
//      前半部分的尾节点
        ListNode oldTail = head;
//      后半部分的头结点 有可能是null
        ListNode oldHead = head.next;
        for (int i = 0; i < m - 1; i++) {
            oldTail = newTail;
            newTail = newTail.next;
        }
//        System.out.println("oldTail newTail: " + oldTail.val + "  " + newTail.val);
//        这个时候newTail指向的为要翻转的位置
        ListNode newHead = newTail;
//      走完之后等于有了一个新的链表
        for (int i = 0; i < n - m; i++) {
//            原链表往后走
            ListNode p = newTail.next;
            oldHead = p.next;
            p.next = newHead;
            newHead = p;
            newTail.next = oldHead;
//            System.out.println("newHead newTail oldHead is" + newHead.val+" "+ newTail.val+" "+oldHead.val);
        }
//        System.out.println("newHead newTail oldHead is" + newHead.val+" "+ newTail.val+" "+oldHead.val);
//        System.out.println(newHead.next.val);
        if (m == 1) {
            newTail.next = oldHead;
            return newHead;
        } else {
            oldTail.next = newHead;
            newTail.next = oldHead;
            return head;
        }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node4.next = new ListNode(5);
        node3.next = node4;
        node2.next = node3;
        head.next = node2;

        reverseBetween(head, 2, 4);
    }

}
