package com.xuzhongjian.leetcode.greedy;

public class Main {
    public static void main(String[] args) {
        int[] gas = {2, 1, 5, 0, 4, 6};
        IncreasingTriplet main = new IncreasingTriplet();
        System.out.println(main.increasingTriplet(gas));
        int[] gxzas = {5, 4, 3, 2, 1};
        System.out.println(main.increasingTriplet(gxzas));
    }
}
