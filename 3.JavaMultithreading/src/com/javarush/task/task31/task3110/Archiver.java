package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by engelsun on 5/4/2017.
 */
public class Archiver {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter an absolute path to archive");
        String pathToZipFile = reader.readLine();
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(pathToZipFile));

        System.out.println("Enter an absolute path to the file which you wish to archive");
        String pathToFile = reader.readLine();
        zipFileManager.createZip(Paths.get(pathToFile));

        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();
    }
}
