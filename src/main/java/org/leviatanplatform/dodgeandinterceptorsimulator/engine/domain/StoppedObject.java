package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class StoppedObject implements MobileObject {

    private Position position;
    private double radius;


    public StoppedObject(Position position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Position getPosition(double time) {
        return position;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
