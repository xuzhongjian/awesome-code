package com.xuzhongjian.leetcode.design;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


/**
 * 274
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
public class RandomizedSet {

    private final HashSet<Integer> set;
    private final Random random;

    public RandomizedSet() {
        set = new HashSet<>();
        random = new Random();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        int size = set.size();
        int i = random.nextInt(size);
        List<Integer> list = new ArrayList<>(set);
        return list.get(i);
    }
}