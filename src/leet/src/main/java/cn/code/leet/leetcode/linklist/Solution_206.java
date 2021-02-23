package cn.code.leet.leetcode.linklist;


import cn.code.leet.structure.ListNode;

import java.util.PriorityQueue;

//经典翻转链表
public class Solution_206 {

    public ListNode reverseList(ListNode head) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(2);
        //头插法生成一个新的链表
        //新的链表头
        ListNode nHead = null;
        while (head != null) {
            //  把head按照头插法插入到新的链表中去
            ListNode p = head.next;
            head.next = nHead;
            nHead = head;
            head = p;
        }
        return nHead;
    }

    //k个一组翻转链表
//    在翻转链表的基础上把头和尾记录一下
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode result = null;
        ListNode nTail = null;
        ListNode lastTail = null;
//        记录当前位置
        ListNode p = head;
        boolean headGet = false;
        while (p != null) {
            ListNode nHead = null;
//          换到最后 p会成为新的尾节点
            nTail = p;
//            如果长度不够 那就不用翻转 nHead = p
            if (!checkLength(p, k)) {
                nHead = p;
                if (lastTail != null)
                    lastTail.next = nHead;
                break;
            }
            for (int i = 0; i < k; i++) {
                if (p == null)
                    break;
//              头插法插入新的
                ListNode n = p.next;
                p.next = nHead;
                nHead = p;
                p = n;
            }
            if (!headGet) {
                result = nHead;
                headGet = true;
            }
            if (lastTail != null) {
                lastTail.next = nHead;
            }
            lastTail = nTail;
        }
        return result;
    }

    public boolean checkLength(ListNode p, int k) {
        ListNode node = p;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
            if (len == k)
                return true;
        }
        return false;
    }
}
