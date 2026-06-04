package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Projectile implements MobileObject {

    private final Position initialPosition;
    private final Velocity velocity;
    private final double radius;
    private boolean destroyed = false;

    public Projectile(Position initialPosition, Velocity velocity, double radius) {
        this.initialPosition = initialPosition;
        this.velocity = velocity;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Position getPosition(double time) {
        return velocity.getFinalPosition(initialPosition, time);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }
}
