package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc101_Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;

        if (n1 != null && n2 != null) {
            return n1.val == n2.val
                    && isMirror(n1.left, n2.right)
                    && isMirror(n1.right, n2.left);
        }

        return false;
    }

    @Test
    public void test01() {
        lc101_Symmetric_Tree solu = new lc101_Symmetric_Tree();

        Assertions.assertTrue(solu.isSymmetric(Common.deserialize(new Integer[]{1,2,2,3,4,4,3})));
        Assertions.assertFalse(solu.isSymmetric(Common.deserialize(new Integer[]{1,2,2,null,3,null,3})));
    }
}
