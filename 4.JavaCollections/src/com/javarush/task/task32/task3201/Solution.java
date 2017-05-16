package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            randomAccessFile.seek(number > randomAccessFile.length() ? randomAccessFile.length() : number);
            randomAccessFile.write(text.getBytes()); // why not "randomAccessFile.writeBytes(text)" ?
        }
    }
}
