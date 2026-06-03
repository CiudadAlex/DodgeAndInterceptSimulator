package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.PlayerDodger;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    public static PlayerDodger buildPlayerDodgerWithCustomStrategy(Environment env, StoppedObject target, Position initialPositionDodger, int radiusDodger, double timeStepDodger, double velocityDodgerModule) {

        List<Movement> listMovement = List.of(
                Movement.UP, Movement.UP, Movement.UP, Movement.UP,
                Movement.UP_RIGHT, Movement.UP_RIGHT, Movement.UP_RIGHT, Movement.UP_RIGHT,
                Movement.RIGHT, Movement.RIGHT,
                Movement.DOWN_RIGHT, Movement.DOWN_RIGHT, Movement.DOWN_RIGHT, Movement.DOWN_RIGHT,
                Movement.DOWN, Movement.DOWN, Movement.DOWN, Movement.DOWN
        );

        Dodger dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, new ArrayList<>());
        double time = 0;

        for (Movement movement : listMovement) {
            dodger.changeDirection(time, movement, velocityDodgerModule);
            time++;
        }

        PlayerDodger playerDodger = new PlayerDodger(env, target);
        playerDodger.setStrategy(dodger);
        return playerDodger;
    }
}
