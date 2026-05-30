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
    }

    // FIXME finish

    private List<Movement> calculateStrategy(Position initialPositionDodger, double radiusDodger) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        int precision = 5;
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, listMobileObject, positionTarget, precision, TIME_STEP, VELOCITY_DODGER_MODULE);
    }

    public Dodger getCurrentDodger(double time) {

        int timeIndex = (int) Math.round(time / TIME_STEP);

        Dodger dodger = new Dodger(initialPositionDodger, Velocity.ZERO, radiusDodger);
        return dodger;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
