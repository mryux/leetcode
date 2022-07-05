package com.leetcode;

public class lc116_Populating_Next_Right_Pointers_in_Each_Node {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    class MyQueue {
        Node head;
        Node tail;
        int size;

        MyQueue() {
            size = 0;
        }

        public void add(Node node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public Node poll() {
            Node ret = null;

            if (head != null) {
                ret = head;
                head = head.next;
                ret.next = null;

                size--;
            }

            return ret;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int size() {
            return size;
        }
    }

    public Node connect(Node root) {
        if (root == null)
            return null;

        MyQueue queue = new MyQueue();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();

                if (n.left != null)
                    queue.add(n.left);
                if (n.right != null)
                    queue.add(n.right);

                if (pre != null) {
                    pre.next = n;
                }
                pre = n;
            }
        }

        return root;
    }
}
