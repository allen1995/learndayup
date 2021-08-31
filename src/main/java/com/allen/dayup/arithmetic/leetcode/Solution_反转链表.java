package com.allen.dayup.arithmetic.leetcode;

import java.util.Stack;

class Solution_反转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode Two = new ListNode(2);
        ListNode Three = new ListNode(3);
        ListNode Four = new ListNode(4);

        head.next = Two;
        Two.next = Three;
        Three.next = Four;

        ListNode node = reverseList_recursion(head);

        printNode(node);

    }

    private static void printNode(ListNode node) {
        while ( node != null ) {

            System.out.print(node.val + " ");
            node = node.next;
        }

        System.out.println();
    }

    public static ListNode reverseList_stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        //遍历链表，压栈
        while(head != null) {
            stack.push(head);
            head = head.next;
        }

        if( stack.isEmpty() ) {
            return null;
        }

        ListNode node = stack.pop();
        ListNode dummp = node;

        while(!stack.isEmpty()) {
            ListNode temp = stack.pop();
            node.next = temp;
            node = node.next;
        }

        node.next = null;
        return dummp;
    }


    public static ListNode reverseList_recursion(ListNode head) {
        if( head == null || head.next == null ) {
            return head;
        }

        ListNode newNode = reverseList_recursion(head.next);

        head.next.next = head;
        head.next = null;

        return newNode;

    }

    public static ListNode reverseList(ListNode head) {

        ListNode newNode = null;
        while ( head != null ) {
            //保存下一条链表
            ListNode temp = head.next;

            head.next = newNode;

            newNode = head;

            head = temp;
        }

        return newNode;
    }
}

