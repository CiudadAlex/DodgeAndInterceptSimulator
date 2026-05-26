package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.List;

public interface Environment {

    List<Projectile> getProjectiles();

    default Projectile buildProjectile(double x, double y, double vx, double vy, double radius) {

        Position initialPosition = new Position(x, y);
        Velocity velocity = new Velocity(vx, vy);

        return new Projectile(initialPosition, velocity, radius);
    }
}
