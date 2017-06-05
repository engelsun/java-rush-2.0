package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by engelsun on 6/5/2017.
 */
public class Solution {
    public static void main(String[] args){
        MaleFactory maleFactory = new MaleFactory();
        System.out.println(maleFactory.getPerson(99));
        System.out.println(maleFactory.getPerson(4));
        System.out.println(maleFactory.getPerson(15));
    }
}
