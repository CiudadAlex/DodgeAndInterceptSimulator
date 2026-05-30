package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger extends Projectile {

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

    public void changeDirection(double time, Movement movement, double velocityModule) {

        initialPosition = getPosition(time);
        velocity = Velocity.calculateVelocity(movement, velocityModule);
        timeLastChange = time;
        listMovement.add(movement);
    }

    public Dodger cloneDodgerWithChangeDirection(double time, Movement movement, double velocityModule) {
        Dodger newDodger = new Dodger(initialPosition, velocity, radius, listMovement);
        newDodger.changeDirection(time, movement, velocityModule);
        return newDodger;
    }

    public List<Movement> getListMovement() {
        return listMovement;
    }
}
