package com.xuzhongjian.leetcode.list;

import java.util.HashSet;
import java.util.Set;

public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }
}
