package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.PlayerDodger;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.PlayerShooter;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    public static Player buildPlayer(Action action, Environment env, StoppedObject target, Position initialPositionDodger, int radiusDodger, double timeStepDodger, double velocityDodgerModule,
                                     double securityMargin, int collisionPrecision, int maxProcessableDodgers, double velocityShootModule, double radiusShoot) {

        if (Action.DODGE.equals(action)) {
            return new PlayerDodger(initialPositionDodger, radiusDodger, securityMargin, env, target, velocityDodgerModule, timeStepDodger, collisionPrecision, maxProcessableDodgers);

        } else if(Action.DODGE_CUSTOM_STRATEGY.equals(action)) {
            return buildPlayerDodgerWithCustomStrategy(env, target, initialPositionDodger, radiusDodger, timeStepDodger, velocityDodgerModule);

        } else if(Action.SHOOT.equals(action)) {
            StoppedObject self = new StoppedObject(initialPositionDodger, radiusDodger);
            return new PlayerShooter(self, env, target, velocityShootModule, radiusShoot);
        }

        return null;
    }

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

    public enum Action {
        DODGE,
        DODGE_CUSTOM_STRATEGY,
        SHOOT;
    }
}
