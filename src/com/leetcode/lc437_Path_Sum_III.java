package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class lc437_Path_Sum_III {
    public int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        //visit_by_list(root, new ArrayList<>(), targetSum);
        visit(root, new int[100], 0, targetSum);

        return ans;
    }

    private int ans = 0;
    private void visit(TreeNode node, int[] prefixSum, int size, int target) {
        if (node == null)
            return;

        int lastVal = 0;
        if (size > 0) {
            lastVal = prefixSum[size-1];
        }

        int currVal = lastVal + node.val;
        for (int i = 0; i < size; i++) {
            if (currVal - prefixSum[i] == target) {
                ans++;
            }
        }

        if (currVal == target)
            ans++;

        prefixSum[size] = currVal;
        visit(node.left, prefixSum, size+1, target);
        visit(node.right, prefixSum, size+1, target);
    }

    private void visit_by_list(TreeNode node, List<Integer> prefixSum, int target) {
        if (node == null)
            return;

        int lastVal = 0;
        int size = prefixSum.size();
        if (size > 0) {
            lastVal = prefixSum.get(size-1);
        }

        // sum from any node in path to this node == target
        int currVal = lastVal + node.val;
        for (int i = 0; i < size; i++) {
            if (currVal - prefixSum.get(i) == target) {
                ans++;
            }
        }
        // sum from root to this node == target
        if (currVal == target)
            ans++;

        prefixSum.add(currVal);
        visit_by_list(node.left, prefixSum, target);
        visit_by_list(node.right, prefixSum, target);
        prefixSum.remove(size);
    }

    @Test
    public void test01() {
        lc437_Path_Sum_III solu = new lc437_Path_Sum_III();

        Assertions.assertEquals(1, solu.pathSum(Common.deserialize(new Integer[]{1,2}), 2));
        Assertions.assertEquals(3, solu.pathSum(Common.deserialize(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22));
        Assertions.assertEquals(3, solu.pathSum(Common.deserialize(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1}), 8));
    }
}
