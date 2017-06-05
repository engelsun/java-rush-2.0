package com.javarush.task.task37.task3702;

/**
 * Created by engelsun on 6/5/2017.
 */
public interface AbstractFactory<T extends Human> {
    T getPerson(int age);
}
