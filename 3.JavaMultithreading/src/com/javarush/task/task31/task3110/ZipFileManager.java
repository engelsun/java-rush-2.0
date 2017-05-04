package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by engelsun on 5/4/2017.
 */
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            String fileName = source.getFileName().toString();
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);

            try (InputStream inputStream = Files.newInputStream(source)) {
                int length;
                byte[] buffer = new byte[8 * 1024];
                while ((length = inputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, length);
                }
            }
            zipOutputStream.closeEntry();
        }
    }
}
