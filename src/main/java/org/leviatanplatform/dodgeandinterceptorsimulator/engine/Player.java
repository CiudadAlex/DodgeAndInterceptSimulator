package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class Player {

    private static final double VELOCITY_DODGER_MODULE = 1;
    private static final double TIME_STEP = 1;
    private final Environment environment;
    private final StoppedObject target;
    private final double radiusDodger;

    private final List<Movement> listMovement;
    private Dodger currentDodger;
    private int currentTimeIndex;

    public Player(Position initialPositionDodger, double radiusDodger, Environment environment, StoppedObject target) {
        this.environment = environment;
        this.target = target;
        this.radiusDodger = radiusDodger;
        this.listMovement = calculateStrategy(initialPositionDodger, radiusDodger);

        this.currentDodger = new Dodger(initialPositionDodger, Velocity.ZERO, radiusDodger);
        this.currentTimeIndex = -1;
    }

    private List<Movement> calculateStrategy(Position initialPositionDodger, double radiusDodger) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        int precision = 5;
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, listMobileObject, positionTarget, precision, TIME_STEP, VELOCITY_DODGER_MODULE);
    }

    public Dodger getDodger(double time) {

        int timeIndex = (int) Math.floor(time / TIME_STEP);

        if (timeIndex >= listMovement.size()) {
            return null;
        }

        if (timeIndex == this.currentTimeIndex) {
            return currentDodger;
        }

        if (timeIndex == this.currentTimeIndex + 1) {

            Position currentPosition = currentDodger.getPosition(time);
            this.currentDodger = buildFollowingDodger(timeIndex, currentPosition);
            this.currentTimeIndex = timeIndex;
            return this.currentDodger;
        }

        throw new RuntimeException("Expected timeIndex = " + this.currentTimeIndex + 1 + " but found " + timeIndex);
    }

    private Dodger buildFollowingDodger(int timeIndex, Position currentPosition) {

        Movement movement = listMovement.get(timeIndex);
        Velocity velocity = Velocity.calculateVelocity(movement, VELOCITY_DODGER_MODULE);

        return new Dodger(currentPosition, velocity, radiusDodger);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
