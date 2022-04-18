package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        List<Integer> res = new ArrayList<>();

        while (top <= bottom && left <= right) {
            visit(matrix, left++, top++, right--, bottom--, res);
        }

        return res;
    }

    private void visit(int[][] matrix, int left, int top, int right, int bottom, List<Integer> list) {
        if (left == right) {
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][left]);
            }
            return;
        }

        if (top == bottom) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            return;
        }

        // visit top row
        for (int i = left; i < right; i++) {
            list.add(matrix[top][i]);
        }
        // visit right column
        for (int i = top; i < bottom; i++) {
            list.add(matrix[i][right]);
        }
        // visit bottom row
        for (int i = right; i > left; i--) {
            list.add(matrix[bottom][i]);
        }
        // visit left column
        for (int i = bottom; i > top; i--) {
            list.add(matrix[i][left]);
        }
    }

    @Test
    public void test01() {
        lc54_SpiralMatrix solu = new lc54_SpiralMatrix();

        Assertions.assertArrayEquals(new int[]{1,2,3,6,9,8,7,4,5}, ToArray(solu.spiralOrder(new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        })));
        Assertions.assertArrayEquals(new int[]{1,2,3,4,8,12,11,10,9,5,6,7}, ToArray(solu.spiralOrder(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        })));
    }

    private int[] ToArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}
