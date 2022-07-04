package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc98_Validate_Binary_Search_Tree {
    public boolean isValidBST(TreeNode root) {
        return visit(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean visit(TreeNode node, long min, long max) {
        if (node == null)
            return true;

        if (node.val >= max || node.val <= min)
            return false;

        return visit(node.left, min, node.val)
                && visit(node.right, node.val, max);
    }

    @Test
    public void test01() {
        lc98_Validate_Binary_Search_Tree solu = new lc98_Validate_Binary_Search_Tree();

        Assertions.assertTrue(solu.isValidBST(Common.deserialize(new Integer[]{2,1,3})));
        Assertions.assertFalse(solu.isValidBST(Common.deserialize(new Integer[]{5,1,4,null,null,3,6})));
    }
}
