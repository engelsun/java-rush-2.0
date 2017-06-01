package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by engelsun on 6/1/2017.
 */
public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            if (message.contains(":")) {
                String userName = message.substring(0, message.indexOf(":"));
                String text = message.substring(message.indexOf(":") + 2);
                switch (text) {
                    case "дата":
                        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("d.MM.YYYY"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, date));
                        break;
                    case "день":
                        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, day));
                        break;
                    case "месяц":
                        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, month));
                        break;
                    case "год":
                        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, year));
                        break;
                    case "время":
                        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, time));
                        break;
                    case "час":
                        String hour = LocalTime.now().format(DateTimeFormatter.ofPattern("H"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, hour));
                        break;
                    case "минуты":
                        String minutes = LocalTime.now().format(DateTimeFormatter.ofPattern("m"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, minutes));
                        break;
                    case "секунды":
                        String seconds = LocalTime.now().format(DateTimeFormatter.ofPattern("s"));
                        sendTextMessage(String.format("Информация для %s: %s", userName, seconds));
                        break;
                }
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }
}
