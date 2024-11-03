package com.xuzhongjian.leetcode.array;

/**
 * 189
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        this.reserve(nums, 0, nums.length - 1);
        this.reserve(nums, 0, k - 1);
        this.reserve(nums, k, nums.length - 1);
    }

    public void reserve(int[] nums, int start, int end) {
        while (start < end) {
            this.swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
