package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.FrontProjectilesEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.NothingEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.DodgeAndInterceptorVisualizer;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    // FIXME test

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        int pixelScale = 10;
        int radius = 1;
        double securityMargin = 0;
        double visualizerTimeDelta = 0.01;
        int visualizerSleepMillis = 10;
        double velocityDodgerModule = 1;
        double timeStepDodger = 1;
        int collisionPrecision = 20;
        int maxProcessableDodgers = 10000;

        Position initialPositionDodger = new Position(-4, 0);
        Environment env = getEnvironment();
        StoppedObject target = new StoppedObject(new Position(4, 0), radius);
        Player player = new Player(initialPositionDodger, radius, securityMargin, env, target, velocityDodgerModule, timeStepDodger, collisionPrecision, maxProcessableDodgers);

        SwingUtilities.invokeLater(() -> {
            DodgeAndInterceptorVisualizer visualizer = new DodgeAndInterceptorVisualizer(player, w, h, pixelScale, visualizerTimeDelta, visualizerSleepMillis);
            visualizer.show();
        });
    }

    private Dodger buildStrategy(Position initialPositionDodger, int radiusDodger, double timeStepDodger, double velocityDodgerModule) {
        Dodger dodger = new Dodger(initialPositionDodger, radiusDodger, timeStepDodger, new ArrayList<>());
        dodger.changeDirection(0, Movement.UP_RIGHT,velocityDodgerModule);
        dodger.changeDirection(1, Movement.UP_RIGHT,velocityDodgerModule);
        dodger.changeDirection(2, Movement.RIGHT,velocityDodgerModule);
        dodger.changeDirection(3, Movement.RIGHT,velocityDodgerModule);
        dodger.changeDirection(4, Movement.RIGHT,velocityDodgerModule);
        dodger.changeDirection(5, Movement.RIGHT,velocityDodgerModule);
        dodger.changeDirection(6, Movement.RIGHT,velocityDodgerModule);
        dodger.changeDirection(7, Movement.DOWN_RIGHT,velocityDodgerModule);
        dodger.changeDirection(8, Movement.DOWN_RIGHT,velocityDodgerModule);
        return dodger;
    }

    private static Environment getEnvironment() {
        Environment nothingEnvironment = new NothingEnvironment();
        Environment frontProjectilesEnvironment = new FrontProjectilesEnvironment(1, 10, 3, 3);
        return frontProjectilesEnvironment;
    }
}