package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Projectile {

    private final Position initialPosition;
    private final Velocity velocity;

    public Projectile(Position initialPosition, Velocity velocity) {
        this.initialPosition = initialPosition;
        this.velocity = velocity;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public Velocity getVelocity() {
        return velocity;
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
