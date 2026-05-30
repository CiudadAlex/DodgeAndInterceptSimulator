package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Velocity {

    public static final Velocity ZERO = new Velocity(0, 0);
    private static final double SQRT_2 = Math.sqrt(2);

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

    public static Velocity calculateVelocity(Movement movement, double velocityModule) {

        double vx = 0;
        double vy = 0;

        switch (movement) {
            case UP -> vy = velocityModule;
            case UP_RIGHT -> {
                vx = velocityModule / SQRT_2;
                vy = velocityModule / SQRT_2;
            }
            case RIGHT -> vx = velocityModule;
            case DOWN_RIGHT -> {
                vx = velocityModule / SQRT_2;
                vy = - velocityModule / SQRT_2;
            }
            case DOWN -> vy = - velocityModule;
            case DOWN_LEFT -> {
                vx = - velocityModule / SQRT_2;
                vy = - velocityModule / SQRT_2;
            }
            case LEFT -> vx = - velocityModule;
            case UP_LEFT -> {
                vx = - velocityModule / SQRT_2;
                vy = velocityModule / SQRT_2;
            }
        }

        return new Velocity(vx, vy);
    }
}
