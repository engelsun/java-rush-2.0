package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Paths;

/**
 * Created by engelsun on 5/4/2017.
 */
public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Добавление нового файла в архив.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полный путь к файлу для добавления:");
            zipFileManager.addFile(Paths.get(ConsoleHelper.readString()));

            ConsoleHelper.writeMessage("Файл добавлен в архив.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Файл не найден.");
        }
    }
}
