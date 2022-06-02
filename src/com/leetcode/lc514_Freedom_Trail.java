package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc514_Freedom_Trail {
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] buf = ring.toCharArray();

        for (int i = 0; i < buf.length; i++) {
            if (!map.containsKey(buf[i]))
                map.put(buf[i], new ArrayList<>());

            map.get(buf[i]).add(i);
        }

        return visit(key.toCharArray(), 0, 0, buf.length, map, new int[key.length()][buf.length]);
    }

    private int visit(char[] arr, int idx, int currPos, int total, Map<Character, List<Integer>> map, int[][] cache) {
        if (idx >= arr.length)
            return 0;

        if (cache[idx][currPos] > 0)
            return cache[idx][currPos];

        int res = Integer.MAX_VALUE;

        for (int next : map.get(arr[idx])) {
            int steps = next > currPos ? Math.min(next-currPos, currPos+total-next) : Math.min(currPos-next, next+total-currPos);
            res = Math.min(res, steps+1+visit(arr, idx+1, next, total, map, cache));
        }

        cache[idx][currPos] = res;
        return res;
    }

    @Test
    public void test01() {
        lc514_Freedom_Trail solu = new lc514_Freedom_Trail();

        Assertions.assertEquals(4, solu.findRotateSteps("godding", "gd"));
        Assertions.assertEquals(13, solu.findRotateSteps("godding", "godding"));
    }
}
