package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger extends Projectile {

    private final Position finalPosition;
    private final double timeInterval;
    private final List<MovementAndTime> listMovementAndTime;

    private double timeLastChange = 0;

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval) {
        this(initialPosition, velocity, radius, timeInterval, new ArrayList<>());
    }

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval, List<MovementAndTime> listMovementAndTime) {
        super(initialPosition, velocity, radius);
        this.timeInterval = timeInterval;
        this.finalPosition = velocity.getFinalPosition(initialPosition, timeInterval);
        this.listMovementAndTime = new ArrayList<>(listMovementAndTime);
    }

    public Position getPosition(double time) {
        return super.getPosition(time - timeLastChange);
    }

    public void changeDirection(double time, Movement movement, double velocityModule) {

        initialPosition = getPosition(time);
        velocity = Velocity.calculateVelocity(movement, velocityModule);
        timeLastChange = time;
        listMovementAndTime.add(new MovementAndTime(movement, time));
    }

    public Dodger cloneDodgerWithChangeDirection(double time, Movement movement, double velocityModule, double timeInterval) {
        Dodger newDodger = new Dodger(initialPosition, velocity, radius, timeInterval, listMovementAndTime);
        newDodger.changeDirection(time, movement, velocityModule);
        return newDodger;
    }

    public List<MovementAndTime> getListMovementAndTime() {
        return listMovementAndTime;
    }
}
