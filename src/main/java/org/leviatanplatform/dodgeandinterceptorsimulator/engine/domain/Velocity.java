package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Velocity {

    private final double x;
    private final double y;

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
