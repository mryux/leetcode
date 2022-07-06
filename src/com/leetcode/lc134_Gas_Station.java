package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class lc134_Gas_Station {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;
        int[] helper = new int[N*2];

        for (int i = 0; i < N; i++) {
            helper[i] = gas[i] - cost[i];
            helper[i+N] = helper[i];
        }

        for (int i = 1; i < helper.length; i++) {
            helper[i] += helper[i-1];
        }

        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!window.isEmpty() && helper[window.peekLast()] >= helper[i])
                window.removeLast();
            window.addLast(i);
        }

        int offset = 0;
        for (int i = 0, j = N; i < N; i++, j++) {
            if (helper[window.peekFirst()] - offset >= 0)
                return i;

            offset = helper[i];

            if (window.peekFirst() == i) {
                window.removeFirst();
            }

            while (!window.isEmpty() && helper[window.peekLast()] >= helper[j])
                window.removeLast();
            window.addLast(j);
        }

        return -1;
    }

    public int canCompleteCircuit01(int[] gas, int[] cost) {
        int tank = 0;

        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
        }

        if (tank < 0) return -1;
        int start = 0;
        int accumulate = 0;

        for (int i = 0; i < gas.length; i++) {
            int currGain = gas[i] - cost[i];

            if (accumulate + currGain < 0) {
                start = i + 1;
                accumulate = 0;
            } else {
                accumulate += currGain;
            }
        }
        return start;
    }

    @Test
    public void test01() {
        lc134_Gas_Station solu = new lc134_Gas_Station();

        Assertions.assertEquals(3, solu.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        Assertions.assertEquals(-1, solu.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}
