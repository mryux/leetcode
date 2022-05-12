package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc687_Longest_Univalue_Path {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;

        Info info = visit(root);

        return Math.max(info.allTreePath-1, info.fromHeadPath-1);
    }

    private class Info {
        public int fromHeadPath;
        public int allTreePath;

        public Info(int fromHeadPath, int allTreePath) {
            this.fromHeadPath = fromHeadPath;
            this.allTreePath = allTreePath;
        }
    }

    private Info visit(TreeNode node) {
        if (node == null)
            return null;

        Info lcInfo = visit(node.left);
        Info rcInfo = visit(node.right);
        int fromHeadPath = 1;
        int allTreePath = 1;

        if (lcInfo != null) {
            if (node.val == node.left.val) {
                fromHeadPath = Math.max(fromHeadPath, lcInfo.fromHeadPath + 1);
            }
            allTreePath = Math.max(Math.max(allTreePath, lcInfo.allTreePath), lcInfo.fromHeadPath);
        }

        if (rcInfo != null) {
            if (node.val == node.right.val) {
                fromHeadPath = Math.max(fromHeadPath, rcInfo.fromHeadPath + 1);
            }
            allTreePath = Math.max(Math.max(allTreePath, rcInfo.allTreePath), rcInfo.fromHeadPath);
        }

        if (lcInfo != null && rcInfo != null) {
            if (node.val == node.left.val && node.val == node.right.val) {
                allTreePath = Math.max(allTreePath, lcInfo.fromHeadPath + 1 + rcInfo.fromHeadPath);
            }
        }

        return new Info(fromHeadPath, allTreePath);
    }

    @Test
    public void test01() {
        lc687_Longest_Univalue_Path solu = new lc687_Longest_Univalue_Path();

        Assertions.assertEquals(1, solu.longestUnivaluePath(Common.deserialize(new Integer[]{1,2,3,4,2})));
//        Assertions.assertEquals(2, solu.longestUnivaluePath(Common.deserialize(new Integer[]{5,4,5,1,1,null,5})));
//        Assertions.assertEquals(2, solu.longestUnivaluePath(Common.deserialize(new Integer[]{1,4,5,4,4,null,5})));
    }
}
