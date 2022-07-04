package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lc102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                list.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            ans.add(list);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc102_Binary_Tree_Level_Order_Traversal solu = new lc102_Binary_Tree_Level_Order_Traversal();

        List<List<Integer>> ans = solu.levelOrder(Common.deserialize(new Integer[]{3,9,20,null,null,15,7}));
        Assertions.assertEquals(3, ans.size());
        Assertions.assertArrayEquals(new int[]{3}, Common.ToArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{9,20}, Common.ToArray(ans.get(1)));
        Assertions.assertArrayEquals(new int[]{15,7}, Common.ToArray(ans.get(2)));

        ans = solu.levelOrder(Common.deserialize(new Integer[]{1}));
        Assertions.assertEquals(1, ans.size());
        Assertions.assertArrayEquals(new int[]{1}, Common.ToArray(ans.get(0)));

//        ans = solu.levelOrder(Common.deserialize(new Integer[]{}));
//        Assertions.assertEquals(1, ans.size());
//        Assertions.assertArrayEquals(new int[]{}, Common.ToArray(ans.get(0)));
    }
}
