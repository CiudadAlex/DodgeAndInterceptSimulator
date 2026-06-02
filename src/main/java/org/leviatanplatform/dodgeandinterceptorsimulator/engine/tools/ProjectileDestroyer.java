package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Velocity;

import java.util.function.Function;

public class ProjectileDestroyer {

    private static final int SEARCH_GRANULARITY = 100;

    public static Velocity calculateVelocityToInterceptProjectile(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Position positionTarget = projectile.getInitialPosition();
        Velocity velocityTarget = projectile.getVelocity();
        Function<Double, Double> functionToFindRootVx = buildFunctionToFindRootVx(projectile, velocityModuleInterceptor, initialPositionInterceptor);

        // FIXME use RootFinder

        double stepVelocity = 2 * velocityModuleInterceptor / SEARCH_GRANULARITY;

        for (int i = 0; i <= SEARCH_GRANULARITY; i++) {
            double vxS = - velocityModuleInterceptor + i * stepVelocity;
            double result = functionToFindRootVx.apply(vxS);
            double time = getTime(initialPositionInterceptor, positionTarget, vxS, velocityTarget);
            System.out.println(result + "    " + time);
        }

        return null;
    }

    private static Function<Double, Double> buildFunctionToFindRootVx(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Position positionTarget = projectile.getInitialPosition();
        Velocity velocityTarget = projectile.getVelocity();
        double vxT = velocityTarget.getVx();
        double vyT = velocityTarget.getVy();

        double Aox = getAox(initialPositionInterceptor, positionTarget);
        double Aoy = getAoy(initialPositionInterceptor, positionTarget);

        return (vxS) -> (vyT - getValueOtherVelocityComponent(velocityModuleInterceptor, vxS)) * Aox - (vxT - vxS) * Aoy;
    }

    private static double getValueOtherVelocityComponent(double velocityModuleInterceptor, double vi) {
        double vM2 = velocityModuleInterceptor * velocityModuleInterceptor;
        double vi2 = vi * vi;
        return Math.sqrt(vM2 - vi2);
    }

    private static double getTime(Position initialPositionInterceptor, Position positionTarget, double vxS, Velocity velocityTarget) {
        double Aox = getAox(initialPositionInterceptor, positionTarget);
        double vxT = velocityTarget.getVx();
        return Aox / (vxS - vxT);
    }

    private static double getAox(Position initialPositionInterceptor, Position positionTarget) {

        double xT = positionTarget.getX();
        double xS = initialPositionInterceptor.getX();
        return xT - xS;
    }

    private static double getAoy(Position initialPositionInterceptor, Position positionTarget) {

        double yT = positionTarget.getY();
        double yS = initialPositionInterceptor.getY();
        return yT - yS;
    }
}
