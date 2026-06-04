package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Velocity;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.math.RootFinder;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.math.model.Range;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ProjectileDestroyer {

    private static final int SEARCH_GRANULARITY = 100;
    private static final double MAX_ROOT_DEVIATION_FROM_ZERO = 0.00000001;

    public static List<Projectile> calculateProjectileToInterceptProjectile(List<Projectile> listProjectile, double velocityModuleInterceptor, Position initialPositionInterceptor, double radiusInterceptor) {
        return listProjectile.stream()
                .map(projectile -> calculateProjectileToInterceptProjectile(projectile, velocityModuleInterceptor, initialPositionInterceptor, radiusInterceptor))
                .filter(Objects::nonNull)
                .toList();
    }

    public static Projectile calculateProjectileToInterceptProjectile(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor, double radiusInterceptor) {

        Velocity velocity = calculateVelocityToInterceptProjectile(projectile, velocityModuleInterceptor, initialPositionInterceptor);

        if (velocity == null) {
            return null;
        }

        return new Projectile(initialPositionInterceptor, velocity, radiusInterceptor);
    }

    protected static Velocity calculateVelocityToInterceptProjectile(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor) {

        Position positionTarget = projectile.getInitialPosition();
        Velocity velocityTarget = projectile.getVelocity();
        boolean takePositiveRoot = false;

        Function<Double, Double> functionToFindRootVx = buildFunctionToFindRootVx(projectile, velocityModuleInterceptor, initialPositionInterceptor, takePositiveRoot);
        Function<Double, Boolean> acceptableVx = buildFunctionAcceptableVx(initialPositionInterceptor, positionTarget, velocityTarget);
        Range rangeX = new Range(-velocityModuleInterceptor, velocityModuleInterceptor);

        Double rootVx = RootFinder.findRootValueForX(functionToFindRootVx, rangeX, SEARCH_GRANULARITY, acceptableVx, MAX_ROOT_DEVIATION_FROM_ZERO);

        if (rootVx == null) {
            return null;
        }

        // FIXME review vy sign
        double vy = getValueOtherVelocityComponent(velocityModuleInterceptor, rootVx, takePositiveRoot);

        return new Velocity(rootVx, vy);
    }

    private static Function<Double, Boolean> buildFunctionAcceptableVx(Position initialPositionInterceptor, Position positionTarget, Velocity velocityTarget) {

        return (vxS) -> {
            double time = getTime(initialPositionInterceptor, positionTarget, vxS, velocityTarget);

            if (time < 0 || time == Double.POSITIVE_INFINITY || Double.isNaN(time)) {
                return false;
            }

            return true;
        };
    }

    private static Function<Double, Double> buildFunctionToFindRootVx(Projectile projectile, double velocityModuleInterceptor, Position initialPositionInterceptor, boolean takePositiveRoot) {

        Position positionTarget = projectile.getInitialPosition();
        Velocity velocityTarget = projectile.getVelocity();
        double vxT = velocityTarget.getVx();
        double vyT = velocityTarget.getVy();

        double Aox = getAox(initialPositionInterceptor, positionTarget);
        double Aoy = getAoy(initialPositionInterceptor, positionTarget);

        return (vxS) -> (vyT - getValueOtherVelocityComponent(velocityModuleInterceptor, vxS, takePositiveRoot)) * Aox - (vxT - vxS) * Aoy;
    }

    private static double getValueOtherVelocityComponent(double velocityModuleInterceptor, double vi, boolean takePositiveRoot) {
        double vM2 = velocityModuleInterceptor * velocityModuleInterceptor;
        double vi2 = vi * vi;
        double sign = takePositiveRoot ? 1 : -1;
        return sign * Math.sqrt(vM2 - vi2);
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
