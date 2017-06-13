package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.FileStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by engelsun on 6/11/2017.
 */
public class Solution {
    public static void main(String[] args) {
        StorageStrategy strategy;
        strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new OurHashMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new FileStorageStrategy();
        testStrategy(strategy, 300);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        strings.forEach(s -> ids.add(shortener.getId(s)));
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        keys.forEach(k -> strings.add(shortener.getString(k)));
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> generatedStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            generatedStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date start, end;
        long duration;

        start = new Date();
        Set<Long> keys = getIds(shortener, generatedStrings);
        end = new Date();
        duration = end.getTime() - start.getTime();
        Helper.printMessage(Long.toString(duration));

        start = new Date();
        Set<String> receivedStrings = getStrings(shortener, keys);
        end = new Date();
        duration = end.getTime() - start.getTime();
        Helper.printMessage(Long.toString(duration));

        boolean setsAreEquals = generatedStrings.equals(receivedStrings);
        if (setsAreEquals) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
