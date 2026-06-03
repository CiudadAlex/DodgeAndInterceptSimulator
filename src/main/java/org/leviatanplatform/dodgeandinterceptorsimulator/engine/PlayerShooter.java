package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.List;

public class PlayerShooter implements Player {

    private final StoppedObject self;
    private final Environment environment;
    private final StoppedObject target;
    private final List<Projectile> shoots;


    public PlayerShooter(StoppedObject self, Environment environment, StoppedObject target) {
        this.self = self;
        this.environment = environment;
        this.target = target;
        this.shoots = calculateShots();
    }

    private List<Projectile> calculateShots() {
        // FIXME finish
        return List.of();
    }

    @Override
    public MobileObject getSelf(double time) {
        return self;
    }

    @Override
    public List<Projectile> getShoots() {
        return shoots;
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
