package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by engelsun on 6/8/2017.
 */
public class Model {
    private Service service = new Service();

    public List<String> getStringDataList() {
        return service.getData();
    }
}
