package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class Player {
    private final Environment environment;
    private final StoppedObject target;
    private final double radiusDodger;
    private final double velocityDodgerModule;
    private final double timeStepDodger;
    private final int collisionPrecision;

    private final List<Movement> listMovement;
    private Dodger currentDodger;
    private int currentTimeIndex;

    public Player(Position initialPositionDodger, double radiusDodger, double securityMargin, Environment environment, StoppedObject target, double velocityDodgerModule, double timeStepDodger, int collisionPrecision) {
        this.environment = environment;
        this.target = target;
        this.radiusDodger = radiusDodger;
        this.velocityDodgerModule = velocityDodgerModule;
        this.timeStepDodger = timeStepDodger;
        this.collisionPrecision = collisionPrecision;
        this.listMovement = calculateStrategy(initialPositionDodger, radiusDodger, securityMargin);

        this.currentDodger = new Dodger(initialPositionDodger, Velocity.ZERO, radiusDodger);
        this.currentTimeIndex = -1;
    }

    private List<Movement> calculateStrategy(Position initialPositionDodger, double radiusDodger, double securityMargin) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, securityMargin, listMobileObject, positionTarget, collisionPrecision, timeStepDodger, velocityDodgerModule);
    }

    public Dodger getDodger(double time) {

        int timeIndex = (int) Math.floor(time / timeStepDodger);

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
        Velocity velocity = Velocity.calculateVelocity(movement, velocityDodgerModule);

        return new Dodger(currentPosition, velocity, radiusDodger);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
