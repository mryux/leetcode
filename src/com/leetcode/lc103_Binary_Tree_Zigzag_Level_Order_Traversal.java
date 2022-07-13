package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> deque = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (leftToRight)
                    deque.addLast(node.val);
                else
                    deque.addFirst(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            leftToRight = !leftToRight;
            ans.add(deque);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc103_Binary_Tree_Zigzag_Level_Order_Traversal solu = new lc103_Binary_Tree_Zigzag_Level_Order_Traversal();

        List<List<Integer>> ans = solu.zigzagLevelOrder(Common.deserialize(new Integer[]{3,9,20,null,null,15,7}));
        Assertions.assertEquals(3, ans.size());
        Assertions.assertArrayEquals(new int[]{3}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{20,9}, Common.toArray(ans.get(1)));
        Assertions.assertArrayEquals(new int[]{15,7}, Common.toArray(ans.get(2)));

        ans = solu.zigzagLevelOrder(Common.deserialize(new Integer[]{1}));
        Assertions.assertEquals(1, ans.size());
        Assertions.assertArrayEquals(new int[]{1}, Common.toArray(ans.get(0)));
    }
}
