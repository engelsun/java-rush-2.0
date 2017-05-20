package com.javarush.task.task25.task2515;

/**
 * Created by engelsun on 5/20/2017.
 */
public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void draw() {

    }

    public void move() {

    }

    public void die() {
        isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        double x = this.x - o.x;
        double y = this.y - o.y;
        double distanceBetweenObjects = Math.sqrt(x * x + y * y);
        double maxRadiusBetweenObjects = Math.max(this.radius, o.radius);
        return distanceBetweenObjects < maxRadiusBetweenObjects;
    }
}
