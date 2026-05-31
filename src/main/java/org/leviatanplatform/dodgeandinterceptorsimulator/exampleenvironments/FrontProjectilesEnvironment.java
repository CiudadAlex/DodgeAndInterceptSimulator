package org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;

import java.util.ArrayList;
import java.util.List;

public class FrontProjectilesEnvironment implements Environment {

    private final double moduleVelocity;
    private final double initialX;
    private final double verticalSeparation;
    private final int numProjectiles;

    public FrontProjectilesEnvironment(double moduleVelocity, double initialX, double verticalSeparation, int numProjectiles) {
        this.moduleVelocity = moduleVelocity;
        this.initialX = initialX;
        this.verticalSeparation = verticalSeparation;
        this.numProjectiles = numProjectiles;
    }

    @Override
    public List<Projectile> getProjectiles() {

        List<Projectile> listProjectile = new ArrayList<>();

        double verticalDisplacement = (numProjectiles - 1) / 2.0;

        for (int i = 0; i < numProjectiles; i++) {
            listProjectile.add(buildProjectile(initialX,  i * verticalSeparation - verticalDisplacement, -moduleVelocity, 0, 1));
        }

        return listProjectile;
    }

}
