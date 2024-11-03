package com.xuzhongjian.leetcode.array;

/**
 * 26
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index == 0 || nums[i] != nums[index - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
