package com.xuzhongjian.leetcode.array;

import java.util.Arrays;

/**
 * 274
 */
public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int hIndex = Math.min(citations[0], citations.length);
        for (int i = 0; i < citations.length; i++) {
            int remainArticleCount = citations.length - i;
            int curHIndex = Math.min(citations[i], remainArticleCount);
            hIndex = Math.max(curHIndex, hIndex);
        }
        return hIndex;
    }
}
