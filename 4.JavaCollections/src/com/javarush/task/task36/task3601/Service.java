package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by engelsun on 6/8/2017.
 */
public class Service {
    private List<String> data;

    public List<String> getData() {
        data = new ArrayList<String>() {{
            add("First string");
            add("Second string");
            add("Third string");
        }};
        return data;
    }
}
