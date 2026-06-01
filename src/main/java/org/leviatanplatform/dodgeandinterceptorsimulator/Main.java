package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.FrontProjectilesEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.NothingEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.DodgeAndInterceptorVisualizer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // FIXME test

    public static void main(String[] args) {

        boolean autoCalculateStrategy = false;

        int w = 1000;
        int h = 800;
        int pixelScale = 10;
        int radiusDodger = 1;
        int radiusTarget = 1;
        double securityMargin = 0;
        double visualizerTimeDelta = 0.01;
        int visualizerSleepMillis = 2;
        double velocityDodgerModule = 1;
        double timeStepDodger = 1;
        int collisionPrecision = 20;
        int maxProcessableDodgers = 10000;

        Position initialPositionDodger = new Position(-4, 0);
        Environment env = getEnvironment();
        StoppedObject target = new StoppedObject(new Position(4, 0), radiusTarget);
        Player player = autoCalculateStrategy ? new Player(initialPositionDodger, radiusDodger, securityMargin, env, target, velocityDodgerModule, timeStepDodger, collisionPrecision, maxProcessableDodgers)
                : buildPlayerWithCustomStrategy(env, target, initialPositionDodger, radiusDodger, timeStepDodger, velocityDodgerModule);

        SwingUtilities.invokeLater(() -> {
            DodgeAndInterceptorVisualizer visualizer = new DodgeAndInterceptorVisualizer(player, w, h, pixelScale, visualizerTimeDelta, visualizerSleepMillis);
            visualizer.show();
        });
    }

    private static Player buildPlayerWithCustomStrategy(Environment env, StoppedObject target, Position initialPositionDodger, int radiusDodger, double timeStepDodger, double velocityDodgerModule) {

        List<Movement> listMovement = List.of(
                Movement.UP_RIGHT, Movement.UP_RIGHT, Movement.UP_RIGHT, Movement.UP_RIGHT,
                Movement.RIGHT, Movement.RIGHT,
                Movement.DOWN_RIGHT, Movement.DOWN_RIGHT, Movement.DOWN_RIGHT, Movement.DOWN_RIGHT
        );

        Dodger dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, new ArrayList<>());
        double time = 0;

        for (Movement movement : listMovement) {
            dodger.changeDirection(time, movement, velocityDodgerModule);
            time++;
        }

        Player player = new Player(env, target);
        player.setStrategy(dodger);
        return player;
    }

    private static Environment getEnvironment() {
        Environment nothingEnvironment = new NothingEnvironment();
        Environment frontProjectilesEnvironment = new FrontProjectilesEnvironment(1, 4, 3, 3);
        return frontProjectilesEnvironment;
    }
}