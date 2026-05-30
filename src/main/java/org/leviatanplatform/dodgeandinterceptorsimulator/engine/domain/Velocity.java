package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Velocity {

    public static final Velocity ZERO = new Velocity(0, 0);
    private static final double SQRT_2 = Math.sqrt(2);

    private final double vx;
    private final double vy;

    public Velocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public Position getFinalPosition(Position initialPosition, double timeInterval) {

        double x0 = initialPosition.getX();
        double y0 = initialPosition.getY();

        double x = x0 + vx * timeInterval;
        double y = y0 + vy * timeInterval;

        return new Position(x, y);
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
