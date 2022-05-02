package com.leetcode;

import com.leetcode.linkedlist.TreeNode;

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

    public static TreeNode Deserialize(Integer[] arr) {
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
}
