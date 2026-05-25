package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Dodger extends Projectile {

    public Dodger(Position initialPosition, Velocity velocity, double radius) {
        super(initialPosition, velocity, radius);
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
