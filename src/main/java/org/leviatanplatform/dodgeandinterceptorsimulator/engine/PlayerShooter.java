package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools.ProjectileDestroyer;

import java.util.List;

public class PlayerShooter implements Player {

    private final StoppedObject self;
    private final Environment environment;
    private final StoppedObject target;
    private final List<Projectile> shoots;

    public PlayerShooter(StoppedObject self, Environment environment, StoppedObject target, double velocityShootModule, double radiusShoot) {
        this.self = self;
        this.environment = environment;
        this.target = target;
        this.shoots = calculateShots(velocityShootModule, radiusShoot);
    }

    private List<Projectile> calculateShots(double velocityShootModule, double radiusShoot) {
        Position initialPositionInterceptor = self.getPosition(0);
        List<Projectile> listProjectile = environment.getProjectiles();
        return ProjectileDestroyer.calculateProjectileToInterceptProjectile(listProjectile, velocityShootModule, initialPositionInterceptor, radiusShoot);
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
