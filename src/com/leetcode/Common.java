package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Common {
    public static int[] ToArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static <T extends Object> T[] toArray(Class<T> clazz, List<T> list) {
        int size = list.size();
        T[] ans = (T[]) Array.newInstance(clazz, size);

        for (int i = 0; i < size; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static Integer[] toIntegerArray(List<Integer> list) {
        Integer[] arr = new Integer[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static Integer[] serialize(TreeNode root) {
        if (root == null)
            return null;

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();

        que.add(root);
        while (!que.isEmpty()) {
            TreeNode node = que.poll();

            list.add(node == null ? null : node.val);

            if (node != null) {
                que.add(node.left);
                que.add(node.right);
            }
        }

        // trim nulls at the end.
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i) != null)
                break;

            list.remove(i);
        }

        return Common.toIntegerArray(list);
    }

    public static TreeNode deserialize(Integer[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        int idx = 0;
        TreeNode head = new TreeNode(arr[idx++]);
        Queue<TreeNode> que = new LinkedList<>();
        que.add(head);

        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (idx < arr.length && arr[idx] != null) {
                    node.left = new TreeNode(arr[idx]);
                    que.add(node.left);
                }
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    node.right = new TreeNode(arr[idx]);
                    que.add(node.right);
                }
                idx++;
            }
        }

        return head;
    }

    public static ListNode fromArray(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }

        return dummy.next;
    }

    public static int[] fromListNode(ListNode head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return ToArray(list);
    }

    @Test
    public void testTreeNodeSerialization() {
        TreeNode root = Common.deserialize(new Integer[]{1, 2, null, null, 3});
        Integer[] arr = Common.serialize(root);
    }
}
