package com.leetcode.linkedlist.unittest;

import com.leetcode.linkedlist.Node;
import com.leetcode.linkedlist.PartitionOnValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Tests {
    @Test
    public void PartitionOnValueTest() {
        PartitionOnValue t = new PartitionOnValue();

        Node head = arrayToList(new int[]{3,4,2,2,8,9});
        head = t.Partition(head, 11);
        int[] arr = listToArray(head);

        Assertions.assertArrayEquals(new int[]{3,4,2,2,8,9}, arr);
    }

    private Node arrayToList(int[] arr) {
        Node head = new Node(0);

        Node curr = head;
        for (int v : arr) {
            curr.next = new Node(v);
            curr = curr.next;
        }

        return head.next;
    }

    private int[] listToArray(Node head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.value);
            head = head.next;
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }
}
