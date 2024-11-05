package com.xuzhongjian.leetcode.string;

public class Main {
    public static void main(String[] args) {
        Compress main = new Compress();
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(main.compress(chars1));
        char[] chars2 = {'a', 'a', 'b', 'b', 'c'};
        System.out.println(main.compress(chars2));
        char[] chars3 = {'a', 'a', 'b',  'c', 'c', 'c'};
        System.out.println(main.compress(chars3));

    }
}
