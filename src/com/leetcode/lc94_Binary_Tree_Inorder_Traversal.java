package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc94_Binary_Tree_Inorder_Traversal {
    /*
    implement by morris traversal.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        TreeNode mostRight = null;

        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) { // has left child tree.
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            ans.add(curr.val);
            curr = curr.right;
        }

        return ans;
    }

    private void morris(TreeNode head) {
        if (head == null)
            return;

        TreeNode curr = head;
        TreeNode mostRight = null;

        while (curr != null) {
            mostRight = curr.left;
            if (mostRight != null) { // has left child tree.
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = curr  ;
                    curr = curr.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            curr = curr.right;
        }
    }

    @Test
    public void test01() {
        lc94_Binary_Tree_Inorder_Traversal solu = new lc94_Binary_Tree_Inorder_Traversal();

        Assertions.assertArrayEquals(new int[]{1,3,2}, Common.ToArray(solu.inorderTraversal(Common.deserialize(new Integer[]{1,null,2,3}))));
        Assertions.assertArrayEquals(new int[]{}, Common.ToArray(solu.inorderTraversal(Common.deserialize(new Integer[]{}))));
        Assertions.assertArrayEquals(new int[]{1}, Common.ToArray(solu.inorderTraversal(Common.deserialize(new Integer[]{1}))));
    }
}
