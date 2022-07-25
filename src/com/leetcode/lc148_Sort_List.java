package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc148_Sort_List {
    public ListNode sortList(ListNode head) {
        ListNode ret = mergeSort(head);

        return ret;
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode[] nodes = split(head);

        ListNode l = mergeSort(nodes[0]);
        ListNode r = mergeSort(nodes[1]);

        return merge(l, r);
    }

    private ListNode[] split(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = slow;

        while (fast != null) {
            prev = slow;
            slow = slow.next;

            fast = fast.next;
            if (fast == null)
                break;

            fast = fast.next;
        }

        prev.next = null;
        return new ListNode[] {head, slow};
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (l != null && r != null) {
            if (l.val < r.val) {
                curr.next = l;
                l = l.next;
            }
            else {
                curr.next = r;
                r = r.next;
            }
            curr = curr.next;
        }

        if (l != null)
            curr.next = l;
        else
            curr.next = r;

        return dummy.next;
    }

    @Test
    public void test01() {
        lc148_Sort_List solu = new lc148_Sort_List();

        Assertions.assertArrayEquals(new int[]{}, ListNode.toArray(solu.sortList(ListNode.fromArray(new int[]{}))));
        Assertions.assertArrayEquals(new int[]{1,2,3,4}, ListNode.toArray(solu.sortList(ListNode.fromArray(new int[]{4,2,1,3}))));
        Assertions.assertArrayEquals(new int[]{-1,0,3,4,5}, ListNode.toArray(solu.sortList(ListNode.fromArray(new int[]{-1,5,3,4,0}))));
    }
}
