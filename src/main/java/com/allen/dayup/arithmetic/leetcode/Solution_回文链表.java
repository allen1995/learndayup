package com.allen.dayup.arithmetic.leetcode;

public class Solution_回文链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode Two = new ListNode(0);
        ListNode Three = new ListNode(0);


        head.next = Two;
        Two.next = Three;

        Solution_回文链表 solution = new Solution_回文链表();
        boolean result = solution.isPalindrome(head);
        System.out.println(result);
    }

    public boolean isPalindrome(ListNode head) {
        if(length(head) == 1) {
            return true;
        }

        //1.找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        while( fast != null && fast.next != null ) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //说明链表个数为基数
        if ( fast != null ) {
            slow = slow.next;
        }

       
        //2.反转链表后半部分
         slow = reverse(slow);

        //3.比较
        fast = head;

        while( slow != null ) {
            if(slow.val != fast.val ) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        if( head.next == null ) {
            return head;
        }

        ListNode next = head.next;
        ListNode curr = reverse(next);
        next.next = head;
        head.next = null;

        return curr;
    }

    private int length(ListNode head) {
        int length = 0;
        while( head != null ) {
            head = head.next;
            length++;
        }

        return length;
    }


}

