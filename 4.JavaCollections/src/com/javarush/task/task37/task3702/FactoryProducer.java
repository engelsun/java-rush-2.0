package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by engelsun on 6/5/2017.
 */
public class FactoryProducer {
    public enum HumanFactoryType {MALE, FEMALE}

    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType) {
        if (humanFactoryType == HumanFactoryType.MALE) return new MaleFactory();
        else return new FemaleFactory();
    }
}
