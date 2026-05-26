package org.leviatanplatform.dodgeandinterceptorsimulator.engine;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Dodger;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.StoppedObject;

public class World {

    private Dodger dodger;
    private Environment environment;
    private StoppedObject target;

    public World(Dodger dodger, Environment environment, StoppedObject target) {
        this.dodger = dodger;
        this.environment = environment;
        this.target = target;
    }

    public Dodger getDodger() {
        return dodger;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public StoppedObject getTarget() {
        return target;
    }
}
