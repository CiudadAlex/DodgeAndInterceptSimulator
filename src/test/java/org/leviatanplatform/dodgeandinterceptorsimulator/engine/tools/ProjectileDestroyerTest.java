package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Velocity;

class ProjectileDestroyerTest {

    public static void main(String[] args) {

        Position initialPositionTarget = new Position(20, 10);
        Velocity velocityTarget = new Velocity(-1, 0);
        Projectile projectile = new Projectile(initialPositionTarget, velocityTarget, 1);
        double velocityModuleInterceptor = 1.0;
        Position initialPositionInterceptor = new Position(0, 0);
        ProjectileDestroyer.calculateVelocityToInterceptProjectile(projectile, velocityModuleInterceptor, initialPositionInterceptor);
    }
}