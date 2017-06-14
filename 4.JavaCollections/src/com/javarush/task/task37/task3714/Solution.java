package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        StringBuilder romanString = new StringBuilder(s.trim());

        Map<String, Integer> patterns = new LinkedHashMap<>();
        patterns.put("MMM", 3000);
        patterns.put("MM", 2000);
        patterns.put("CM", 900);
        patterns.put("M", 1000);
        patterns.put("DCCC", 800);
        patterns.put("DCC", 700);
        patterns.put("DC", 600);
        patterns.put("CD", 400);
        patterns.put("D", 500);
        patterns.put("CCC", 300);
        patterns.put("CC", 200);
        patterns.put("XC", 90);
        patterns.put("C", 100);
        patterns.put("LXXX", 80);
        patterns.put("LXX", 70);
        patterns.put("LX", 60);
        patterns.put("XL", 40);
        patterns.put("L", 50);
        patterns.put("XXX", 30);
        patterns.put("XX", 20);
        patterns.put("IX", 9);
        patterns.put("X", 10);
        patterns.put("VIII", 8);
        patterns.put("VII", 7);
        patterns.put("VI", 6);
        patterns.put("IV", 4);
        patterns.put("V", 5);
        patterns.put("III", 3);
        patterns.put("II", 2);
        patterns.put("I", 1);


        int sum = 0;
        while (romanString.length() != 0) {
            for (Map.Entry<String, Integer> pair : patterns.entrySet()) {
                int pos = romanString.indexOf(pair.getKey());
                if (pos >= 0) {
                    sum += pair.getValue();
                    romanString.delete(pos, pos + pair.getKey().length());
                }
            }
        }
        return sum;
    }
}
