package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.List;

public interface Player {

    MobileObject getSelf(double time);

    List<Projectile> getShoots();

    Environment getEnvironment();

    StoppedObject getTarget();

    default void destroyProjectiles(double time) {

        Environment env = getEnvironment();
        List<Projectile> listProjectile = env.getProjectiles();
        List<Projectile> listShoot = getShoots();

        for (Projectile projectile : listProjectile) {
            for (Projectile shoot : listShoot) {
                if (isThereCollision(projectile, shoot)) {
                    projectile.destroy();
                }
            }
        }
    }

    default boolean isThereCollision(Projectile projectile1, Projectile projectile2) {
        return false;
    }
}
