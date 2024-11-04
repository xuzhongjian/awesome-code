package com.xuzhongjian.leetcode.string;

/**
 * 3163. String Compression III
 */
public class CompressedString {
    public String compressedString(String word) {
        char[] chars = word.toCharArray();
        int index = 0;
        int count = 0;
        char curChar = chars[0];
        StringBuilder sb = new StringBuilder();
        while (index < chars.length) {
            if (count >= 9 || chars[index] != curChar) {
                sb.append(count).append(curChar);
                count = 0;
                curChar = chars[index];
            }
            if (chars[index] == curChar) {
                count++;
            }

            index++;
        }
        sb.append(count).append(curChar);
        return sb.toString();
    }
}
