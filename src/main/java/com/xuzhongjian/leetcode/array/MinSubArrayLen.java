package com.xuzhongjian.leetcode.array;

/**
 * 209. Minimum Size Subarray Sum
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minSubArray = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // 当 sum >= target 时，缩小窗口
            while (sum >= target) {
                minSubArray = Math.min(minSubArray, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        // 如果 minSubArray 没有更新，说明不存在符合条件的子数组
        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }
}
