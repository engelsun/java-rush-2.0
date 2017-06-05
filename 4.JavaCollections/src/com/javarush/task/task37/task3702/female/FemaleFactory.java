package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by engelsun on 6/5/2017.
 */
public class FemaleFactory implements AbstractFactory{
    @Override
    public Human getPerson(int age) {
        if (age <= KidGirl.MAX_AGE) return new KidGirl();
        else if (KidGirl.MAX_AGE < age && age <= TeenGirl.MAX_AGE) return new TeenGirl();
        else return new Woman();
    }
}
