package com.javarush.task.task37.task3711;

/**
 * Created by engelsun on 6/14/2017.
 */
public class Computer {
    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public Computer() {
        cpu = new CPU();
        hardDrive = new HardDrive();
        memory = new Memory();
    }

    public void run() {
        cpu.calculate();
        hardDrive.storeData();
        memory.allocate();
    }
}
