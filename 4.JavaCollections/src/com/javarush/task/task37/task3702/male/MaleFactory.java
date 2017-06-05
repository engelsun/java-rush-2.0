package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.Human;

/**
 * Created by engelsun on 6/5/2017.
 */
public class MaleFactory<T extends  Human> {
    public T getPerson(int age) {
        if (age <= KidBoy.MAX_AGE) return (T) new KidBoy();
        else if (KidBoy.MAX_AGE < age && age <= TeenBoy.MAX_AGE) return (T) new TeenBoy();
        else return (T) new Man();
    }
}
