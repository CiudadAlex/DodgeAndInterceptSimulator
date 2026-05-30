package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class Player {
    private final Environment environment;
    private final StoppedObject target;
    private final double velocityDodgerModule;
    private final double timeStepDodger;
    private final int collisionPrecision;
    private Dodger dodger;

    public Player(Position initialPositionDodger, double radiusDodger, double securityMargin, Environment environment, StoppedObject target, double velocityDodgerModule, double timeStepDodger, int collisionPrecision) {
        this.environment = environment;
        this.target = target;
        this.velocityDodgerModule = velocityDodgerModule;
        this.timeStepDodger = timeStepDodger;
        this.collisionPrecision = collisionPrecision;

        List<DodgerSegment> listDodgerSegment = calculateStrategy(initialPositionDodger, radiusDodger, securityMargin);
        this.dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, listDodgerSegment);
    }

    private List<DodgerSegment> calculateStrategy(Position initialPositionDodger, double radiusDodger, double securityMargin) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, securityMargin, listMobileObject, positionTarget, collisionPrecision, timeStepDodger, velocityDodgerModule);
    }

    public DodgerSegment getDodgerSegment(double time) {
        return dodger.getDodgerSegment(time);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
