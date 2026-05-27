package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class Player {

    private static final double TIME_STEP = 1;

    private Dodger dodger;
    private Environment environment;
    private StoppedObject target;

    public Player(Dodger dodger, Environment environment, StoppedObject target) {
        this.dodger = dodger;
        this.environment = environment;
        this.target = target;
    }

    private void calculateStrategy() {

        Position positionOrigin = dodger.getPosition(0);
        double radius = dodger.getRadius();
        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        int precision = 5;
        List<Movement> listMovement = NoCollisionPathFinder.findNoCollisionPath(positionOrigin, radius, listMobileObject, positionTarget, precision, TIME_STEP);
    }

    public Dodger getDodger() {
        return dodger;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
