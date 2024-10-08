package com.xuzhongjian.code.leetcode;

/**
 * 1963
 */
public class MinSwaps {

    public static void main(String[] args) {
        MinSwaps minSwaps = new MinSwaps();
        System.out.println(minSwaps.minSwaps("]]][[["));
        System.out.println(minSwaps.minSwaps("[]]][["));
        System.out.println(minSwaps.minSwaps("[]]][["));
        System.out.println(minSwaps.minSwaps("[][][]"));

    }

    private int count = 0;

    public int minSwaps(String s) {
        count = 0;
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right - 1) {
            if (chars[left] == '[' && chars[right] == ']') {
                left++;
                right--;
            } else if (chars[left] == ']' && chars[right] == '[') {
                this.swap(chars, left++, right--);
            } else if (chars[left] == '[' && chars[right] == '[') {
                while (chars[left] == '[' && chars[left + 1] == ']') {
                    left += 2;
                }
            } else if (chars[left] == ']' && chars[right] == ']') {
                while (chars[right] == ']' && chars[right - 1] == '[') {
                    right -= 2;
                }
            }
        }
        return count;
    }

    public void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        count++;
    }
}
