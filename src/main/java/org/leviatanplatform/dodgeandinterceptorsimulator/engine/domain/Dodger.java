package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Dodger extends Projectile {

    private static final double SQRT_2 = Math.sqrt(2);

    private double timeLastChange = 0;

    public Dodger(Position initialPosition, Velocity velocity, double radius) {
        super(initialPosition, velocity, radius);
    }

    public Position getPosition(double time) {
        return super.getPosition(time - timeLastChange);
    }

    public void changeDirection(double time, Movement movement, double velocityModule) {

        initialPosition = getPosition(time);
        velocity = calculateVelocity(movement, velocityModule);
        timeLastChange = time;
    }

    private Velocity calculateVelocity(Movement movement, double velocityModule) {

        double vx = 0;
        double vy = 0;

        switch (movement) {
            case UP -> vy = velocityModule;
            case UP_RIGHT -> {
                vx = SQRT_2 * velocityModule;
                vy = SQRT_2 * velocityModule;
            }
            case RIGHT -> vx = velocityModule;
            case DOWN_RIGHT -> {
                vx = SQRT_2 * velocityModule;
                vy = - SQRT_2 * velocityModule;
            }
            case DOWN -> vy = - velocityModule;
            case DOWN_LEFT -> {
                vx = - SQRT_2 * velocityModule;
                vy = - SQRT_2 * velocityModule;
            }
            case LEFT -> vx = - velocityModule;
            case UP_LEFT -> {
                vx = - SQRT_2 * velocityModule;
                vy = SQRT_2 * velocityModule;
            }
        }

        return new Velocity(vx, vy);
    }
}
