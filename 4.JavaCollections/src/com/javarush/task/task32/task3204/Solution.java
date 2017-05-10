package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.*;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            ByteArrayOutputStream password = getPassword();
            String passwordGen = password.toString();
            boolean isPassword = passwordGen.matches("([a-zA-Z0-9]{2,3}){3}");
            if (isPassword) {
                System.out.println(password.toString() + " - true");
            } else {
                System.out.println(password.toString() + " - false");
            }

        }
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Random random = new Random();
        // numbers
        for (int i = 0; i < 2; i++) {
            byteArrayOutputStream.write(48 + random.nextInt(10));
        }
        // upper characters
        for (int i = 0; i < 3; i++) {
            byteArrayOutputStream.write(97 + random.nextInt(26));
        }
        // lower characters
        for (int i = 0; i < 3; i++) {
            byteArrayOutputStream.write(65 + random.nextInt(26));
        }
        List<Byte> list = new ArrayList<>();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        for (byte b : bytes) {
            list.add(b);
        }
        Collections.shuffle(list);
        byteArrayOutputStream.reset();
        for (int i = 0; i < list.size(); i++) {
            byteArrayOutputStream.write(list.get(i));
        }
        return byteArrayOutputStream;
    }
}