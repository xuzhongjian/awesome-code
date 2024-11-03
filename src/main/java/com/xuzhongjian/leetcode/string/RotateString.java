package com.xuzhongjian.leetcode.string;

/**
 * 796
 */
public class RotateString {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int indexOfS = 0;
        while (indexOfS < s.length()) {
            if (goal.charAt(0) == s.charAt(indexOfS)) {
                int gi = 0;
                int si = indexOfS;
                while (goal.charAt(gi) == s.charAt(si)) {
                    gi++;
                    si++;
                    if (si == s.length()) {
                        si = 0;
                    }
                    if (gi == goal.length()) {
                        return true;
                    }
                }
            }
            indexOfS++;
        }
        return false;
    }
}
