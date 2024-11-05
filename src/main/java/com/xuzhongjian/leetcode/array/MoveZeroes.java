package com.xuzhongjian.leetcode.array;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int target = 0;
        int cur = 0;
        while (true) {
            while (cur < nums.length && nums[cur] == 0) cur++;
            if (cur == nums.length) break;
            nums[target] = nums[cur];
            target++;
            cur++;
        }
        for (int i = target; i < nums.length; i++) nums[i] = 0;

    }
}