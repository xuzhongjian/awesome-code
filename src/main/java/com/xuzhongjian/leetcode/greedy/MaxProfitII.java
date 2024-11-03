package com.xuzhongjian.leetcode.greedy;

/**
 * 122
 */
public class MaxProfitII {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = prices[0];
        int allProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < max) {
                allProfit = max - min + allProfit;
                min = prices[i];
            }
            max = prices[i];
        }
        return allProfit + (max - min);
    }
}
