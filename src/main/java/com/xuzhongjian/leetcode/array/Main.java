package com.xuzhongjian.leetcode.array;

import com.xuzhongjian.leetcode.string.Compress;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        MinSubArrayLen main = new MinSubArrayLen();
        System.out.println(main.minSubArrayLen(11, nums));
    }
}
