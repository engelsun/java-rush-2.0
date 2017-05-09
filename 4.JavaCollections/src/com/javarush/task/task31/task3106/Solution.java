package com.javarush.task.task31.task3106;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        List<String> partsOfZipFile = Arrays.asList(args).subList(1, args.length);
        Collections.sort(partsOfZipFile);

        List<FileInputStream> inputStreamsOfZipParts = new ArrayList<>();
        for (String partOfZipFile :
                partsOfZipFile) {
            inputStreamsOfZipParts.add(new FileInputStream(partOfZipFile));
        }

        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(inputStreamsOfZipParts));

        try (ZipInputStream zipInputStream = new ZipInputStream(sequenceInputStream)) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(resultFileName)) {
                while (zipInputStream.getNextEntry() != null) {
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = zipInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                }
            }
        }
    }
}

