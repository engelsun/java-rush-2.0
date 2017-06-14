package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

/**
 * Created by engelsun on 6/14/2017.
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setBorderColor(Shape shape) {
        System.out.printf("Setting border color for %s to red.", shape.getClass().getSimpleName());
    }

    @Override
    public void draw() {
        setBorderColor(super.decoratedShape);
        super.draw();
    }
}
