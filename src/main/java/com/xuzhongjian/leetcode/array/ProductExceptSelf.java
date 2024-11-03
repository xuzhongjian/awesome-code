package com.xuzhongjian.leetcode.array;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int countOfZero = this.countOfZero(nums);
        if (countOfZero == 1) {
            int m = 1;
            for (int num : nums) {
                if (num != 0) {
                    m = m * num;
                }
            }
            int indexOfZero = this.indexOfZero(nums);
            result[indexOfZero] = m;
        } else if (countOfZero == 0) {
            long m = 1;
            for (int num : nums) {
                m = m * num;
            }
            for (int i = 0; i < result.length; i++) {
                result[i] = (int) (m / nums[i]);
            }

        }
        return result;
    }

    public int countOfZero(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if (num == 0) {
                result++;
            }
        }
        return result;
    }

    public int indexOfZero(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
