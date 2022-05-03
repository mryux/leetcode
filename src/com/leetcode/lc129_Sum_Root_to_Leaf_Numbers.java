package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc129_Sum_Root_to_Leaf_Numbers {
    public int sumNumbers(TreeNode root) {
        ans = 0;
        visit(root, 0);
        return ans;
    }

    private int ans = 0;
    private void visit(TreeNode node, int sum) {
        sum = sum*10 + node.val;
        if (node.left == null && node.right == null)
            ans += sum;

        if (node.left != null)
            visit(node.left, sum);
        if (node.right != null)
            visit(node.right, sum);
    }

    @Test
    public void test01() {
        lc129_Sum_Root_to_Leaf_Numbers solu = new lc129_Sum_Root_to_Leaf_Numbers();

        Assertions.assertEquals(25, solu.sumNumbers(Common.deserialize(new Integer[]{1,2,3})));
        Assertions.assertEquals(1026, solu.sumNumbers(Common.deserialize(new Integer[]{4,9,0,5,1})));
    }
}
