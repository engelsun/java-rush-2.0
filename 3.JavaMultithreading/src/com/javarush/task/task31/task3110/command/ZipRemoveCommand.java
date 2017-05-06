package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by engelsun on 5/4/2017.
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление файла из архива.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите полный путь к файлу в архиве:");
        Path zipFile = Paths.get(ConsoleHelper.readString());

        zipFileManager.removeFile(zipFile);
        ConsoleHelper.writeMessage("Файл удалён из архива.");
    }
}
