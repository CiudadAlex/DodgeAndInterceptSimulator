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

    public Player(Position initialPositionDodger, double radiusDodger, Environment environment, StoppedObject target) {
        this.environment = environment;
        this.target = target;
        this.radiusDodger = radiusDodger;
        this.listMovement = calculateStrategy(initialPositionDodger, radiusDodger);

        this.currentDodger = new Dodger(initialPositionDodger, Velocity.ZERO, radiusDodger);
        int currentTimeIndex = 0;
    }

    // FIXME finish

    private List<Movement> calculateStrategy(Position initialPositionDodger, double radiusDodger) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        int precision = 5;
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, listMobileObject, positionTarget, precision, TIME_STEP, VELOCITY_DODGER_MODULE);
    }

    public Dodger getCurrentDodger(double time) {

        int timeIndex = (int) Math.floor(time / TIME_STEP);

        if (timeIndex >= listMovement.size() ) {
            return null;
        }

        Movement movement = listMovement.get(timeIndex);
        Velocity velocity = Velocity.calculateVelocity(movement, VELOCITY_DODGER_MODULE);

        // FIXME finish: currentDodger, currentTimeIndex

        return new Dodger(initialPositionDodger, velocity, radiusDodger);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
