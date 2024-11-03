package com.xuzhongjian.leetcode.dynamic_programming;

/**
 * 45
 */
public class CanJumpII {
    public int jump(int[] nums) {
        // dp[i] means: the min steps from 0 to index of i
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int minStepOfi = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (i - j <= nums[j]) {
                    minStepOfi = Math.min(minStepOfi, dp[j] + 1);
                }
            }
            dp[i] = minStepOfi;
        }
        return dp[nums.length - 1];
    }
}
