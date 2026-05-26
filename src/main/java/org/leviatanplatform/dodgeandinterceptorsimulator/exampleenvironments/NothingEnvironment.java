package org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;

import java.util.List;

public class NothingEnvironment implements Environment {

    @Override
    public List<Projectile> getProjectiles() {
        return List.of();
    }
}
