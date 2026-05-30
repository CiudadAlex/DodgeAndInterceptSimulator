package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger extends Projectile {

    private double timeLastChange = 0;
    // FIXME finish finalPosition
    private Position finalPosition;
    private double timeInterval;
    private List<Movement> listMovement = new ArrayList<>();

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval) {
        super(initialPosition, velocity, radius);
        this.timeInterval = timeInterval;
    }

    public Dodger(Position initialPosition, Velocity velocity, double radius, double timeInterval, List<Movement> listMovement) {
        super(initialPosition, velocity, radius);
        this.timeInterval = timeInterval;
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
