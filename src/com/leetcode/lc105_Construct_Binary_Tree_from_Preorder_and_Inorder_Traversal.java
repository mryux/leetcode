package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class lc105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return visit(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }

    private TreeNode visit(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1)
            return null;

        int val = preorder[l1];
        TreeNode node = new TreeNode(val);

        if (l1 == r1)
            return node;

        // find index of val in inorder.
        int findIndex = map.get(val);

        node.left = visit(preorder, l1+1, l1+findIndex-l2, inorder, l2, findIndex-1, map);
        node.right = visit(preorder, l1+findIndex-l2+1, r1, inorder, findIndex+1, r2, map);

        return node;
    }

    @Test
    public void test01() {
        lc105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal solu = new lc105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal();

        Assertions.assertArrayEquals(new Integer[]{3,9,20,null,null,15,7},
                Common.serialize(solu.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7})));
        Assertions.assertArrayEquals(new Integer[]{-1},
                Common.serialize(solu.buildTree(new int[]{-1}, new int[]{-1})));
    }
}
