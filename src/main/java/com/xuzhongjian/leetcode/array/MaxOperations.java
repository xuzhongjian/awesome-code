package com.xuzhongjian.leetcode.array;

import java.util.HashMap;

/**
 * 1679. Max Number of K-Sum Pairs
 */
public class MaxOperations {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int num : nums) {
            int complement = k - num;

            // 如果 complement 存在并且次数大于 0，则可以组成一对
            if (map.getOrDefault(complement, 0) > 0) {
                result++;
                map.put(complement, map.get(complement) - 1);  // 更新 complement 的次数
            } else {
                // 否则将 num 放入 map 中
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return result;
    }
}
