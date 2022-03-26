package com.leetcode.linkedlist;

public class PartitionOnValue {
    /*
        比val小值在左边，大的在右边，等于的在中间
     */
    public Node Partition(Node head, int val) {
        if (head == null || head.next == null)
            return head;

        Node lowerHead = new Node(0);
        Node lowerTail = lowerHead;
        Node equalHead = new Node(0);
        Node equalTail = equalHead;
        Node largeHead = new Node(0);
        Node largeTail = largeHead;

        Node curr = head;
        while (curr != null) {
            Node next = curr.next;

            curr.next = null;
            if (curr.value < val) {
                lowerTail.next = curr;
                lowerTail = curr;
            }
            else if (curr.value == val) {
                equalTail.next = curr;
                equalTail = curr;
            }
            else {
                largeTail.next = curr;
                largeTail = curr;
            }

            curr = next;
        }

        equalTail.next = largeHead.next;
        lowerTail.next = equalHead.next;

        return lowerHead.next;
    }
}
