package com.leetcode;

import com.leetcode.linkedlist.TreeNode;

public class lc337_House_Robber_III {
    public int rob(TreeNode root) {
        int[] val = visit(root);

        return Math.max(val[0], val[1]);
    }

    /*
    returns -
    [0]: case no-rob,
    [1]: case rob,
     */
    private int[] visit(TreeNode node) {
        if (node == null) {
            return new int[2];
        }

        int[] left = visit(node.left);
        int[] right = visit(node.right);

        return new int[] {
                Math.max(left[0], left[1]) + Math.max(right[0], right[1]),
                node.val + left[0] + right[0]
        };
    }
}
