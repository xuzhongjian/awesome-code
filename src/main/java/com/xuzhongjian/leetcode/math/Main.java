package com.xuzhongjian.leetcode.math;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        MajorityElement main = new MajorityElement();
        int i = main.majorityElement(nums);
        System.out.println(i);

    }
}
