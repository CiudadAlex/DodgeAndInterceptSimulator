package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Dodger extends Projectile {

    private static final double SQRT_2 = Math.sqrt(2);

    public Dodger(Position initialPosition, Velocity velocity, double radius) {
        super(initialPosition, velocity, radius);
    }

    private void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    private void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void move(Movement movement, double velocity) {

        double vx = 0;
        double vy = 0;

        switch (movement) {
            case UP -> vy = velocity;
            case UP_RIGHT -> {
                vx = SQRT_2 * velocity;
                vy = SQRT_2 * velocity;
            }
            case RIGHT -> vx = velocity;
            case DOWN_RIGHT -> {
                vx = SQRT_2 * velocity;
                vy = - SQRT_2 * velocity;
            }
            case DOWN -> vy = - velocity;
            case DOWN_LEFT -> {
                vx = - SQRT_2 * velocity;
                vy = - SQRT_2 * velocity;
            }
            case LEFT -> vx = - velocity;
            case UP_LEFT -> {
                vx = - SQRT_2 * velocity;
                vy = SQRT_2 * velocity;
            }
        }

        setVelocity(new Velocity(vx, vy));
    }
}
