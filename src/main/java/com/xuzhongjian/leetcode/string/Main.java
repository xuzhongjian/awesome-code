package com.xuzhongjian.leetcode.string;

public class Main {
    public static void main(String[] args) {
        CompressedString main = new CompressedString();
        String b = main.compressedString("abc");
        System.out.println(b);
        b = main.compressedString("abccc");
        System.out.println(b);
        b = main.compressedString("aaa");
        System.out.println(b);
        b = main.compressedString("a");
        System.out.println(b);
        b = main.compressedString("aaabbbccc");
        System.out.println(b);
        b = main.compressedString("aaaaaaaaaaaaaabb");
        System.out.println(b);
    }
}
