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

    public Position getInitialPosition() {
        return initialPosition;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public double getRadius() {
        return radius;
    }

    public Position getPosition(double time) {

        double x0 = initialPosition.getX();
        double y0 = initialPosition.getY();

        double vx = velocity.getX();
        double vy = velocity.getY();

        double x = x0 + vx * time;
        double y = y0 + vy * time;

        return new Position(x, y);
    }
}
