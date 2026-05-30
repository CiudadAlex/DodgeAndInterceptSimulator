package org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;

import java.util.List;

public class FrontProjectilesEnvironment implements Environment {

    private double moduleVelocity;

    public FrontProjectilesEnvironment(double moduleVelocity) {
        this.moduleVelocity = moduleVelocity;
    }

    @Override
    public List<Projectile> getProjectiles() {
        return List.of(
                buildProjectile(10,  3, -moduleVelocity, 0, 1),
                buildProjectile(10,  0, -moduleVelocity, 0, 1),
                buildProjectile(10, -3, -moduleVelocity, 0, 1)
        );
    }

}
