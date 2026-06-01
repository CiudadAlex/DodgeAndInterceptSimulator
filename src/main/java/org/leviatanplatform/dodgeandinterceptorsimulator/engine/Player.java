package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class Player {
    private final Environment environment;
    private final StoppedObject target;
    private Dodger dodger;

    public Player(Position initialPositionDodger, double radiusDodger, double securityMargin, Environment environment, StoppedObject target,
                  double velocityDodgerModule, double timeStepDodger, int collisionPrecision, int maxProcessableDodgers) {
        this.environment = environment;
        this.target = target;

        List<DodgerSegment> listDodgerSegment = calculateStrategy(initialPositionDodger, radiusDodger, securityMargin, maxProcessableDodgers,
                collisionPrecision, timeStepDodger, velocityDodgerModule);
        this.dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, listDodgerSegment);
    }

    private List<DodgerSegment> calculateStrategy(Position initialPositionDodger, double radiusDodger, double securityMargin, int maxProcessableDodgers,
                                                  int collisionPrecision, double timeStepDodger, double velocityDodgerModule) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, securityMargin, listMobileObject, positionTarget,
                collisionPrecision, timeStepDodger, velocityDodgerModule, maxProcessableDodgers);
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
