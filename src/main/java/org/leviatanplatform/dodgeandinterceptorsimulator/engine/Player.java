package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.List;

public interface Player {

    MobileObject getSelf(double time);

    List<Projectile> getShoots();

    Environment getEnvironment();

    StoppedObject getTarget();
}
