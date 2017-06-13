package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Set<Character> chars = new TreeSet<>();
        String file = args[0];
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
        }
        String line = builder.toString().replaceAll("\\p{Space}|\\p{Punct}", "").toLowerCase();
        for (int i = 0; i < line.length(); i++) {
            chars.add(line.charAt(i));
        }
        chars.stream().limit(5).forEach(System.out::print);
    }
}