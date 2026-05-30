package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger extends Projectile {

    private final Position finalPosition;
    private final double timeInterval;
    private final List<Movement> listMovement;

    private double timeLastChange = 0;

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval) {
        this(initialPosition, velocity, radius, timeInterval, new ArrayList<>());
    }

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval, List<Movement> listMovement) {
        super(initialPosition, velocity, radius);
        this.timeInterval = timeInterval;
        this.finalPosition = velocity.getFinalPosition(initialPosition, timeInterval);
        this.listMovement = new ArrayList<>(listMovement);
    }

    public Position getPosition(double time) {
        return super.getPosition(time - timeLastChange);
    }

    public void changeDirection(double time, Movement movement, double velocityModule) {

        initialPosition = getPosition(time);
        velocity = Velocity.calculateVelocity(movement, velocityModule);
        timeLastChange = time;
        listMovement.add(movement);
    }

    public Dodger cloneDodgerWithChangeDirection(double time, Movement movement, double velocityModule, double timeInterval) {
        Dodger newDodger = new Dodger(initialPosition, velocity, radius, timeInterval, listMovement);
        newDodger.changeDirection(time, movement, velocityModule);
        return newDodger;
    }

    public List<Movement> getListMovement() {
        return listMovement;
    }
}
