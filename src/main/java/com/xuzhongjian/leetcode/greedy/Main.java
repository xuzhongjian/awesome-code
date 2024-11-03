package com.xuzhongjian.leetcode.greedy;

public class Main {
    public static void main(String[] args) {
        int[] gas = {3, 1, 1};
        int[] costs = {1, 2, 2};
        CanCompleteCircuit main = new CanCompleteCircuit();
        System.out.println(main.canCompleteCircuit(gas, costs));
    }
}
