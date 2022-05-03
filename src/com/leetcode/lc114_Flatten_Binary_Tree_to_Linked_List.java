package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc114_Flatten_Binary_Tree_to_Linked_List {
    public void flatten(TreeNode root) {
        visit(root);
    }

    class Info {
        TreeNode head;
        TreeNode tail;

        public Info(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    private Info visit(TreeNode node) {
        if (node == null)
            return new Info(null, null);

        Info lcInfo = visit(node.left);
        Info rcInfo = visit(node.right);

        node.left = null;
        node.right = lcInfo.head;
        if (lcInfo.tail != null) {
            lcInfo.tail.right = rcInfo.head;
        } else {
            node.right = rcInfo.head;
        }

        return new Info(node, rcInfo.head == null ? (lcInfo.head == null ? node : lcInfo.tail) : rcInfo.tail);
    }

    @Test
    public void test01() {
        lc114_Flatten_Binary_Tree_to_Linked_List solu = new lc114_Flatten_Binary_Tree_to_Linked_List();

        TreeNode root = Common.deserialize(new Integer[]{1,2,5,3,4,null,6});
        solu.flatten(root);
        Assertions.assertArrayEquals(new Integer[]{1,null,2,null,3,null,4,null,5,null,6}, Common.serialize(root));
    }

}
