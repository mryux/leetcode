package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class lc23_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> que = new PriorityQueue<>((n1,n2) -> n1.val - n2.val);

        for (ListNode h : lists) {
            if (h != null)
                que.add(h);
        }

        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (!que.isEmpty()) {
            ListNode n = que.poll();

            curr.next = n;
            curr = curr.next;

            if (n.next != null)
                que.add(n.next);
        }

        return dummy.next;
    }

    @Test
    public void test01() {
        lc23_Merge_k_Sorted_Lists solu = new lc23_Merge_k_Sorted_Lists();

        ListNode[] heads = new ListNode[3];
        heads[0]  = Common.fromArray(new int[]{1,4,5});
        heads[1]  = Common.fromArray(new int[]{1,3,4});
        heads[2]  = Common.fromArray(new int[]{2,6});

        ListNode ans = solu.mergeKLists(heads);

        Assertions.assertArrayEquals(new int[]{1,1,2,3,4,4,5,6}, Common.fromListNode(ans));
    }

    @Test
    public void test02() {
        lc23_Merge_k_Sorted_Lists solu = new lc23_Merge_k_Sorted_Lists();

        ListNode[] heads = new ListNode[0];
        ListNode ans = solu.mergeKLists(heads);

        Assertions.assertEquals(null, ans);
    }

    @Test
    public void test03() {
        lc23_Merge_k_Sorted_Lists solu = new lc23_Merge_k_Sorted_Lists();

        ListNode[] heads = new ListNode[1];
        ListNode ans = solu.mergeKLists(heads);

        Assertions.assertEquals(null, ans);
    }
}
