package com.xuzhongjian.leetcode.array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < nums2.length; i++) {
            int index1 = nums1.length - 1 - i;
            int index2 = nums2.length - 1 - i;
            nums1[index1] = nums2[index2];
        }
        Arrays.sort(nums1);
    }
}
