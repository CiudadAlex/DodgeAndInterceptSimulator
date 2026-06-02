package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Velocity;

import java.util.function.Function;

public class ProjectileDestroyer {

    public Velocity calculateVelocityToInterceptProjectile(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {



        return null;
    }

    public Function<Double, Double> buildFunctionToFindRootVx(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Position positionTarget = projectile.getInitialPosition();
        Velocity velocityTarget = projectile.getVelocity();

        double Aox = positionTarget.getX() - initialPositionInterceptor.getX();
        double Aoy = positionTarget.getY() - initialPositionInterceptor.getY();

        return (vx) -> vx;
    }
}
