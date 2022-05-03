package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc124_Binary_Tree_Maximum_Path_Sum {
    public int maxPathSum(TreeNode root) {
        Info info = visit(root);

        return Math.max(info.allTreeMaxSum, info.fromHeadMaxSum);
    }

    private Info visit(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info lcInfo = visit(node.left);
        Info rcInfo = visit(node.right);

        int allTreeMaxSum = node.val;
        int fromNodeMaxSum = node.val;

        if (lcInfo != null) {
            allTreeMaxSum = Math.max(allTreeMaxSum, lcInfo.allTreeMaxSum);
            allTreeMaxSum = Math.max(allTreeMaxSum, lcInfo.fromHeadMaxSum + node.val);
            fromNodeMaxSum = Math.max(fromNodeMaxSum, lcInfo.fromHeadMaxSum + node.val);
        }
        if (rcInfo != null) {
            allTreeMaxSum = Math.max(allTreeMaxSum, rcInfo.allTreeMaxSum);
            allTreeMaxSum = Math.max(allTreeMaxSum, rcInfo.fromHeadMaxSum + node.val);
            fromNodeMaxSum = Math.max(fromNodeMaxSum, rcInfo.fromHeadMaxSum + node.val);
        }
        if (lcInfo != null && rcInfo != null)
            allTreeMaxSum = Math.max(allTreeMaxSum, lcInfo.fromHeadMaxSum + node.val + rcInfo.fromHeadMaxSum);

        return new Info(allTreeMaxSum, fromNodeMaxSum);
    }

    class Info {
        int allTreeMaxSum;
        int fromHeadMaxSum;

        public Info(int childSum, int childSumFromNode) {
            this.allTreeMaxSum = childSum;
            this.fromHeadMaxSum = childSumFromNode;
        }
    }

    @Test
    public void test01() {
        lc124_Binary_Tree_Maximum_Path_Sum solu = new lc124_Binary_Tree_Maximum_Path_Sum();

        Assertions.assertEquals(1, solu.maxPathSum(Common.Deserialize(new Integer[] {-2,1})));
        Assertions.assertEquals(6, solu.maxPathSum(Common.Deserialize(new Integer[] {1,2,3})));
        Assertions.assertEquals(42, solu.maxPathSum(Common.Deserialize(new Integer[] {-10,9,20,null,null,15,7})));
    }
}
