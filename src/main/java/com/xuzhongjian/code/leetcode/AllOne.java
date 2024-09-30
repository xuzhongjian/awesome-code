package com.xuzhongjian.code.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AllOne {

    // head.next = max;
    private final Node head;
    // tail.prev = min;
    private final Node tail;

    public AllOne() {
        head = new Node();
        tail = new Node();
        tail.prev = head;
        head.next = tail;
        map = new HashMap<>();
    }

    HashMap<String, Node> map;

    public void inc(String key) {
        Node curNode = map.get(key);
        int newTimes;
        Node prevNode;
        Node nextNode;
        if (curNode != null) {
            curNode.keys.remove(key);
            newTimes = curNode.times + 1;
            prevNode = curNode;
            nextNode = curNode.next;
            if (curNode.keys.size() == 0) {
                prevNode = curNode.prev;
                this.deleteNode(curNode);
            }
        } else {
            newTimes = 1;
            prevNode = tail.prev;
            nextNode = tail;
        }

        if (newTimes == prevNode.times) {
            prevNode.keys.add(key);
            map.put(key, prevNode);
        } else {
            Node newNode = this.newNode(newTimes, key);
            this.insertNode(prevNode, newNode, nextNode);
            map.put(key, newNode);
        }
    }

    private void insertNode(Node prevNode, Node newNode, Node nextNode) {
        if (prevNode != null) {
            prevNode.next = newNode;
        }
        newNode.next = nextNode;
        if (nextNode != null) {
            nextNode.prev = newNode;
        }
        newNode.prev = prevNode;
    }

    private void deleteNode(Node node) {
        Node next = node.next;
        Node prev = node.prev;
        prev.next = next;
        next.prev = prev;
    }

    public void dec(String key) {
        Node curNode = map.get(key);
        if (curNode == null) {
            return;
        }
        curNode.keys.remove(key);
        int newTimes = curNode.times - 1;
        if (newTimes == 0) {
            map.remove(key);
            return;
        }
        Node nextNode = curNode.next;
        if (nextNode.times == newTimes) {
            nextNode.keys.add(key);
            map.put(key, nextNode);
        } else {
            // big ------------------ small
            // curNode <--> newNode <--> nextNode
            Node newNode = this.newNode(newTimes, key);
            this.insertNode(curNode, newNode, nextNode);
        }
        if (curNode.keys.size() == 0) {
            this.deleteNode(curNode);
        }
    }

    public Node newNode(int times, String key) {
        Node newNode = new Node();
        newNode.keys = new HashSet<>();
        newNode.keys.add(key);
        newNode.times = times;
        return newNode;
    }

    public String getMaxKey() {
        Node next = head.next;
        if (next == tail) {
            return "";
        }
        for (String key : next.keys) {
            return key;
        }
        return "";
    }

    public String getMinKey() {
        Node prev = tail.prev;
        if (prev == head) {
            return "";
        }
        for (String key : prev.keys) {
            return key;
        }
        return "";
    }
}

class Node {
    Node prev;
    Node next;
    Set<String> keys;
    int times;
}