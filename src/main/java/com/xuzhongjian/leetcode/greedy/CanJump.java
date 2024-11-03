package com.xuzhongjian.leetcode.greedy;

/**
 * 150
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        int i = 0;
        int maxIndex = nums[0];
        while (true) {
            if (i == nums.length - 1) {
                return true;
            }
            maxIndex = Math.max(maxIndex, nums[i] + i);
            if (i == maxIndex) {
                System.out.println("max index: " + maxIndex);
                return false;
            }
            i++;
        }
    }
}
