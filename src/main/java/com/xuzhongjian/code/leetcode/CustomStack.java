package com.xuzhongjian.code.leetcode;

public class CustomStack {
    ListNode head;
    ListNode tail;
    int maxSize;
    int size;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.head = new ListNode();
        this.tail = new ListNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void push(int x) {
        if (size == maxSize) {
            return;
        }
        ListNode newNode = new ListNode();
        newNode.value = x;
        this.insertNode(this.head, newNode, this.head.next);
        size ++;
    }

    public int pop() {
        if (this.head.next == this.tail) {
            return -1;
        }
        ListNode cur = this.head.next;
        this.deleteNode(cur);
        size --;
        return cur.value;
    }

    private void insertNode(ListNode prevNode, ListNode newNode, ListNode nextNode) {
        prevNode.next = newNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        newNode.prev = prevNode;
    }

    public void increment(int k, int value) {
        int i = 0;
        ListNode cur = this.tail;
        while (i < k) {
            cur = cur.prev;
            if (cur == this.head) {
                break;
            }
            cur.value = cur.value + value;
            i++;
        }
    }

    private void deleteNode(ListNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }
}

class ListNode {
    int value;
    ListNode next;
    ListNode prev;
}