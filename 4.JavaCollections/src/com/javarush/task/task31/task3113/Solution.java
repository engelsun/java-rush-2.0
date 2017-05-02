package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*
Что внутри папки?
*/
public class Solution {
    static int countOfFolders = -1;
    static int countOfFiles = 0;
    static long sizeOfDirectory = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String directoryName = bufferedReader.readLine();
        bufferedReader.close();

        Path directoryPath = Paths.get(directoryName);
        if (!Files.isDirectory(directoryPath)) {
            System.out.println(directoryPath.toAbsolutePath() + " - не папка");
            return;
        } else {
            Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    countOfFolders++;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    countOfFiles++;
                    sizeOfDirectory += Files.size(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        System.out.println("Всего папок - " + countOfFolders);
        System.out.println("Всего файлов - " + countOfFiles);
        System.out.println("Общий размер - " + sizeOfDirectory);
    }
}
