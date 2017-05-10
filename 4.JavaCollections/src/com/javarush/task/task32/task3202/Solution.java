package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(is)) {
                char[] buffer = new char[is.available()];
                int len;
                while ((len = inputStreamReader.read(buffer)) != -1) {
                    stringWriter.write(buffer, 0, len);
                }
            }
        }
        return stringWriter;
    }
}