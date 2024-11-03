package com.xuzhongjian.leetcode.greedy;

/**
 * 121
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                int profit = prices[i] - min;
                maxProfit = Math.max(profit, maxProfit);
            }
        }
        return maxProfit;
    }
}
