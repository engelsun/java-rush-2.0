package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by engelsun on 6/13/2017.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        LocalTime start = LocalTime.now();
        strings.forEach(s -> ids.add(shortener.getId(s)));
        LocalTime end = LocalTime.now();
        return Duration.between(start, end).toMillis();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        LocalTime start = LocalTime.now();
        ids.forEach(id -> strings.add(shortener.getString(id)));
        LocalTime end = LocalTime.now();
        return Duration.between(start, end).toMillis();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> idsMap = new HashSet<>();
        Set<Long> idsBiMap = new HashSet<>();
        long timeMapIds = getTimeForGettingIds(shortener1, origStrings, idsMap);
        long timeBiMapIds = getTimeForGettingIds(shortener2, origStrings, idsBiMap);
        Assert.assertTrue(timeMapIds > timeBiMapIds);

        long timeMapStrings = getTimeForGettingStrings(shortener1, idsMap, new HashSet<String>());
        long timeBiMapStrings = getTimeForGettingStrings(shortener2, idsBiMap, new HashSet<String>());
        Assert.assertEquals(timeMapStrings, timeBiMapStrings, 5);
    }
}
