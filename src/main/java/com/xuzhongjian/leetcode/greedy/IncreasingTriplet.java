package com.xuzhongjian.leetcode.greedy;

/**
 * 334. Increasing Triplet Subsequence
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                // num > second, found a triplet
                return true;
            }
        }
        return false;
    }

}