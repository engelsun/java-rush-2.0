package com.javarush.task.task24.task2413;

/**
 * Created by engelsun on 5/26/2017.
 */
public abstract class BaseObject {
    protected double x;
    protected double y;
    protected double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public abstract void  draw(Canvas canvas);

    public abstract void move();

    public boolean isIntersec(BaseObject o) {
        double x = this.x - o.x;
        double y = this.y - o.y;
        double distanceBetweenObjects = Math.sqrt(x * x + y * y);
        double maxRadiusBetweenObjects = Math.max(this.radius, o.radius);
        return distanceBetweenObjects < maxRadiusBetweenObjects;
    }
}
