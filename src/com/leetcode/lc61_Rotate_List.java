package com.leetcode;

public class lc61_Rotate_List {
    // Given the head of a linked list, rotate the list to the right by k places.
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;

        int len = getLength(head);
        k = k % len;

        ListNode s1 = head;
        for (int i = 0; i < k; i++)
            s1 = s1.next;

        ListNode s2 = head;
        while (s1.next != null) {
            s1 = s1.next;
            s2 = s2.next;
        }

        // s1 points to last node now.
        s1.next = head;
        head = s2.next;
        s2.next = null;
        return head;
    }

    private int getLength(ListNode head) {
        int size = 0;

        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }
}
