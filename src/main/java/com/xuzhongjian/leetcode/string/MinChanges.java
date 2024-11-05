package com.xuzhongjian.leetcode.string;

/**
 * 2914. Minimum Number of Changes to Make Binary String Beautiful
 */
public class MinChanges {
    public int minChanges(String s) {
        int left = 0;
        char[] chars = s.toCharArray();
        int result = 0;
        while (left < chars.length - 1) {
            result = result + this.calculate(chars, left, left + 1);
            left = left + 2;
        }
        return result;
    }

    public int calculate(char[] chars, int left, int right) {
        int length = right - left + 1;
        int sum = 0;
        for (int i = left; i < right + 1; i++) {
            int num = chars[i] - '0';
            sum = num + sum;
        }
        return Math.min(length - sum, sum);
    }
}
