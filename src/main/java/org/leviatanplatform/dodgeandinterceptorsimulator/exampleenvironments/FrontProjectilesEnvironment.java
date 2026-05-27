package org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;

import java.util.List;

public class FrontProjectilesEnvironment implements Environment {

    @Override
    public List<Projectile> getProjectiles() {
        return List.of(
                buildProjectile(10,  3, -1, 0, 1),
                buildProjectile(10,  0, -1, 0, 1),
                buildProjectile(10, -3, -1, 0, 1)
        );
    }

}
