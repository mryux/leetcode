package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc118_Pascals_Triangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        if (numRows > 0) {
            ans.add(List.of(1));
        }
        if (numRows > 1) {
            ans.add(List.of(1,1));
        }

        List<Integer> pre = List.of(1, 1);

        for (int i = 2; i < numRows; i++) {
            List<Integer> curr = new ArrayList<>();

            curr.add(1);
            for (int j = 1; j < i; j++) {
                curr.add(pre.get(j - 1) + pre.get(j));
            }
            curr.add(1);

            ans.add(curr);
            pre = curr;
        }

        return ans;
    }

    @Test
    public void test01() {
        lc118_Pascals_Triangle solu = new lc118_Pascals_Triangle();

        List<List<Integer>> ans = solu.generate(5);

        Assertions.assertEquals(5, ans.size());
        Assertions.assertArrayEquals(Common.ToArray(List.of(1)), Common.ToArray(ans.get(0)));
        Assertions.assertArrayEquals(Common.ToArray(List.of(1,1)), Common.ToArray(ans.get(1)));
        Assertions.assertArrayEquals(Common.ToArray(List.of(1,2,1)), Common.ToArray(ans.get(2)));
        Assertions.assertArrayEquals(Common.ToArray(List.of(1,3,3,1)), Common.ToArray(ans.get(3)));
        Assertions.assertArrayEquals(Common.ToArray(List.of(1,4,6,4,1)), Common.ToArray(ans.get(4)));

        ans = solu.generate(1);
        Assertions.assertEquals(1, ans.size());
        Assertions.assertArrayEquals(Common.ToArray(List.of(1)), Common.ToArray(ans.get(0)));
    }
}
