package com.example.demo;

import java.util.Arrays;
import java.util.List;

public class FEachTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two");
        for (String s : list) {
            System.out.println(s);
        }

    }
}
