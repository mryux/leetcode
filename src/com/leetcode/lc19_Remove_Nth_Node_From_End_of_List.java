package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc19_Remove_Nth_Node_From_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;

        ListNode curr = head;
        ListNode pre = null;

        while (curr != null) {
            n--;

            if (n == -1)
                pre = head;
            else if (n < -1)
                pre = pre.next;

            curr = curr.next;
        }

        if (n > 0)
            return head;

        if (n == 0)
            return head.next;

        pre.next = pre.next.next;
        return head;
    }

    @Test
    public void test01() {
        lc19_Remove_Nth_Node_From_End_of_List solu = new lc19_Remove_Nth_Node_From_End_of_List();

        Assertions.assertEquals(null, solu.removeNthFromEnd(Common.fromArray(new int[]{1}), 1));
        Assertions.assertEquals(1, solu.removeNthFromEnd(Common.fromArray(new int[]{1,2}), 1).val);
    }
}
