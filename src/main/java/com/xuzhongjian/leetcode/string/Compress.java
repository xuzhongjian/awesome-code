package com.xuzhongjian.leetcode.string;

public class Compress {
    public int compress(char[] chars) {
        char curChar = chars[0];
        int curCount = 0;
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            if (aChar == curChar) {
                curCount++;
            } else {
                sb.append(curChar);
                if (curCount != 1) {
                    sb.append(curCount);
                }
                curChar = aChar;
                curCount = 1;
            }
        }
        sb.append(curChar);
        if (curCount != 1) {
            sb.append(curCount);
        }
        char[] result = sb.toString().toCharArray();
        System.arraycopy(result, 0, chars, 0, result.length);
        return result.length;
    }
}
