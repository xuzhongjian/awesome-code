package com.xuzhongjian.leetcode.array;

/**
 * 80
 */
public class RemoveDuplicatesII {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index == 0 || index == 1 || nums[i] != nums[index - 1] || nums[i] != nums[index - 2]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
