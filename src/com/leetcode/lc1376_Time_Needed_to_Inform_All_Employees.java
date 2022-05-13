package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc1376_Time_Needed_to_Inform_All_Employees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // build manager map
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1)
                continue;

            if (!map.containsKey(manager[i]))
                map.put(manager[i], new ArrayList<>());

            map.get(manager[i]).add(i);
        }

        return visit(headID, map, informTime);
    }

    private int visit(int manager, Map<Integer, List<Integer>> map, int[] informTime) {
        if (!map.containsKey(manager)) {
            return informTime[manager];
        }

        List<Integer> subordinators = map.get(manager);
        int max = 0;

        for (int subordinator : subordinators) {
            max = Math.max(max, visit(subordinator, map, informTime));
        }

        return max + informTime[manager];
    }

    @Test
    public void test01() {
        lc1376_Time_Needed_to_Inform_All_Employees solu = new lc1376_Time_Needed_to_Inform_All_Employees();

        Assertions.assertEquals(0, solu.numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));
        Assertions.assertEquals(1, solu.numOfMinutes(6, 2, new int[]{2,2,-1,2,2,2}, new int[]{0,0,1,0,0,0}));
    }
}
