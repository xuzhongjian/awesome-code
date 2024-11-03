package com.xuzhongjian.leetcode.greedy;

public class CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] delta = new int[gas.length];
        int sum = 0;
        for (int i = 0; i < delta.length; i++) {
            delta[i] = gas[i] - cost[i];
            sum += delta[i];
        }
        if (sum < 0) {
            return -1;
        }

        int currentGas = 0;
        int startingIndex = 0;
        for (int i = 0; i < delta.length; i++) {
            currentGas += delta[i];
            if (currentGas < 0) {
                currentGas = 0;
                startingIndex = i + 1;
            }
        }
        return startingIndex;
    }

}