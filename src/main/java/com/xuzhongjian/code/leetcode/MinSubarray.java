package com.xuzhongjian.code.leetcode;

import java.util.Arrays;

public class MinSubarray {
    public int minSubarray(int[] nums, int p) {
        int sum = Arrays.stream(nums).sum();
        int target = sum % p;
        if (target == 0) {
            return 0;
        }
        int i = -1, j = 0;
        while (j < nums.length) {

        }
        return -1;
    }

    private int sumSubString(int[] nums, int i, int j) {
        int result = 0;
        for (int index = i; index <= j; index++) {
            result += nums[index];
        }
        return result;
    }

}
