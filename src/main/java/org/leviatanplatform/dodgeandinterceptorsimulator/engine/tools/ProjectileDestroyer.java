package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Velocity;

import java.util.function.Function;

public class ProjectileDestroyer {

    private static final int SEARCH_GRANULARITY = 100;

    public static Velocity calculateVelocityToInterceptProjectile(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Function<Double, Double> functionToFindRootVx = buildFunctionToFindRootVx(projectile, velocityModuleInterceptor, initialPositionInterceptor);

        double stepVelocity = 2 * velocityModuleInterceptor / SEARCH_GRANULARITY;

        for (int i = 0; i <= SEARCH_GRANULARITY; i++) {
            double vx = - velocityModuleInterceptor + i * stepVelocity;
            double result = functionToFindRootVx.apply(vx);
            System.out.println(result);
        }

        return null;
    }

    private static Function<Double, Double> buildFunctionToFindRootVx(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Position positionTarget = projectile.getInitialPosition();
        double xT = positionTarget.getX();
        double yT = positionTarget.getY();
        double xS = initialPositionInterceptor.getX();
        double yS = initialPositionInterceptor.getY();

        Velocity velocityTarget = projectile.getVelocity();
        double vxT = velocityTarget.getVx();
        double vyT = velocityTarget.getVy();

        double Aox = xT - xS;
        double Aoy = yT - yS;

        return (vxS) -> (vyT - getValueOtherComponent(velocityModuleInterceptor, vxS)) * Aox - (vxT - vxS) * Aoy;
    }

    private static double getValueOtherComponent(double velocityModuleInterceptor, double vi) {
        double vM2 = velocityModuleInterceptor * velocityModuleInterceptor;
        double vi2 = vi * vi;
        return Math.sqrt(vM2 - vi2);
    }
}
