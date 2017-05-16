package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            randomAccessFile.seek(number);
            byte[] buffer = new byte[text.length()];
            randomAccessFile.read(buffer, 0, text.length());

            randomAccessFile.seek(randomAccessFile.length());
            if (convertByteToString(buffer).equals(text)) {
                randomAccessFile.write("true".getBytes());
            } else {
                randomAccessFile.write("false".getBytes());
            }
        }

    }

    public static String convertByteToString(byte readBytes[]) {
        return new String(readBytes);
    }
}
