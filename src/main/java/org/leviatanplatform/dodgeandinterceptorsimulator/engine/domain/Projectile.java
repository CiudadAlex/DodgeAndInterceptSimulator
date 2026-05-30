package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Projectile implements MobileObject {

    protected Position initialPosition;
    protected Velocity velocity;
    protected double radius;

    public Projectile(Position initialPosition, Velocity velocity, double radius) {
        this.initialPosition = initialPosition;
        this.velocity = velocity;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public Position getPosition(double time) {
        return velocity.getFinalPosition(initialPosition, time);
    }
}
