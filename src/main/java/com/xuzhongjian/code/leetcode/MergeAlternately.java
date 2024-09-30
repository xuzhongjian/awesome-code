package com.xuzhongjian.code.leetcode;

public class MergeAlternately {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int max = Math.max(word1.length(), word2.length());
        while (i < max) {
            sb.append(getCharFromString(i, word1));
            sb.append(getCharFromString(i, word2));
            i++;
        }
        return sb.toString();
    }

    public String getCharFromString(int index, String str) {
        if (index < str.length()) {
            return String.valueOf(str.charAt(index));
        }
        return "";
    }
}
