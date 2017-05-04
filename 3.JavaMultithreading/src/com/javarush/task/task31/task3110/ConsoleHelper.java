package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by engelsun on 5/4/2017.
 */
public class ConsoleHelper {
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid data! Please enter a line of characters or/and numbers");
        }
        return line;
    }

    public static int readInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = 0;
        try {
            number = Integer.valueOf(reader.readLine());
        } catch (IOException e) {
            System.out.println("Invalid data! Please enter only number without characters");
        }
        return number;
    }
}
