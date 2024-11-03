package com.xuzhongjian.leetcode.list;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode cur = result;
        boolean addition = false;
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0)
                    + (l2 != null ? l2.val : 0)
                    + (addition ? 1 : 0);
            addition = sum >= 10;
            ListNode listNode = new ListNode(sum % 10);
            cur.next = listNode;
            cur = listNode;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (addition) cur.next = new ListNode(1);

        return result.next;
    }
}
