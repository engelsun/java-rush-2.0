package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;

/**
 * Created by engelsun on 5/4/2017.
 */
public class ExitCommand extends ConsoleHelper implements Command{

    @Override
    public void execute() throws Exception {
        String exitMessage = "До встречи!";
        writeMessage(exitMessage);
    }
}
