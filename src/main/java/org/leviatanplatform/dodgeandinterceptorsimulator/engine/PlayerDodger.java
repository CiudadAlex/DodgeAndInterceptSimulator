package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.NoCollisionPathFinder;

import java.util.List;

public class PlayerDodger implements Player {
    private final Environment environment;
    private final StoppedObject target;
    private Dodger dodger;

    public PlayerDodger(Position initialPositionDodger, double radiusDodger, double securityMargin, Environment environment, StoppedObject target,
                        double velocityDodgerModule, double timeStepDodger, int collisionPrecision, int maxProcessableDodgers) {
        this(environment, target);

        List<DodgerSegment> listDodgerSegment = calculateStrategy(initialPositionDodger, radiusDodger, securityMargin, maxProcessableDodgers,
                collisionPrecision, timeStepDodger, velocityDodgerModule);
        this.dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, listDodgerSegment);
    }

    public PlayerDodger(Environment environment, StoppedObject target) {
        this.environment = environment;
        this.target = target;
    }

    public void setStrategy(Dodger dodger) {
        this.dodger = dodger;
    }

    private List<DodgerSegment> calculateStrategy(Position initialPositionDodger, double radiusDodger, double securityMargin, int maxProcessableDodgers,
                                                  int collisionPrecision, double timeStepDodger, double velocityDodgerModule) {

        List<Projectile> listMobileObject = environment.getProjectiles();
        Position positionTarget = target.getPosition(0);
        return NoCollisionPathFinder.findNoCollisionPath(initialPositionDodger, radiusDodger, securityMargin, listMobileObject, positionTarget,
                collisionPrecision, timeStepDodger, velocityDodgerModule, maxProcessableDodgers);
    }

    @Override
    public DodgerSegment getDodgerSegment(double time) {
        return dodger.getDodgerSegment(time);
    }

    @Override
    public List<Projectile> getShoots() {
        return List.of();
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public StoppedObject getTarget() {
        return target;
    }
}
