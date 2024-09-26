package com.xuzhongjian.code.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 729. My Calendar I
 * <a href="https://leetcode.com/problems/my-calendar-i/description/">URL</a>
 */
public class MyCalendar {
    private List<int[]> list;

    public MyCalendar() {
        list = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        if (judge(start, end)) {
            list.add(new int[]{start, end});
            return true;
        }
        return false;
    }

    public boolean judge(int start, int end) {
        for (int[] range : list) {
            if ((start <= range[0] && range[0] < end) ||
                    (range[0] < start && start < range[1])) {
                return false;
            }
        }
        return true;
    }
}
