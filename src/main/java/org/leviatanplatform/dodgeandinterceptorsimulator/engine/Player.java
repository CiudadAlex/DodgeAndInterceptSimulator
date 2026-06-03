package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.DodgerSegment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Projectile;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.StoppedObject;

import java.util.List;

public interface Player {

    DodgerSegment getDodgerSegment(double time);

    List<Projectile> getShoots();

    Environment getEnvironment();

    StoppedObject getTarget();
}
