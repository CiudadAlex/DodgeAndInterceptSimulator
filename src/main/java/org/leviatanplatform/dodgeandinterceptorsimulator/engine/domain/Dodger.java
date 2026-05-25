package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger extends Projectile {

    private static final double SQRT_2 = Math.sqrt(2);
    private static final double VELOCITY_MODULE = 1;

    private double timeLastChange = 0;
    private List<Movement> listMovement = new ArrayList<>();

    public Dodger(Position initialPosition, Velocity velocity, double radius) {
        super(initialPosition, velocity, radius);
    }

    public Dodger(Position initialPosition, Velocity velocity, double radius, List<Movement> listMovement) {
        super(initialPosition, velocity, radius);
        this.listMovement = new ArrayList<>(listMovement);
    }

    public Position getPosition(double time) {
        return super.getPosition(time - timeLastChange);
    }

    public void changeDirection(double time, Movement movement) {

        initialPosition = getPosition(time);
        velocity = calculateVelocity(movement, VELOCITY_MODULE);
        timeLastChange = time;
        listMovement.add(movement);
    }

    public Dodger cloneDodgerWithChangeDirection(double time, Movement movement) {
        Dodger newDodger = new Dodger(initialPosition, velocity, radius, listMovement);
        newDodger.changeDirection(time, movement);
        return newDodger;
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
