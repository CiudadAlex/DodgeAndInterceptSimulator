package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.FrontProjectilesEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.NothingEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.DodgeAndInterceptorVisualizer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        int pixelScale = 5;
        int radius = 1;
        double securityMargin = 0;
        double visualizerTimeDelta = 0.01;
        int visualizerSleepMillis = 10;
        double velocityDodgerModule = 1;
        double timeStepDodger = 1;
        int collisionPrecision = 20;

        Position initialPositionDodger = new Position(-4, 0);
        Environment env = getEnvironment();
        StoppedObject target = new StoppedObject(new Position(4, 0), radius);
        Player player = new Player(initialPositionDodger, radius, securityMargin, env, target, velocityDodgerModule, timeStepDodger, collisionPrecision);

        SwingUtilities.invokeLater(() -> {
            DodgeAndInterceptorVisualizer visualizer = new DodgeAndInterceptorVisualizer(player, w, h, pixelScale, visualizerTimeDelta, visualizerSleepMillis);
            visualizer.show();
        });
    }

    private static Environment getEnvironment() {
        Environment nothingEnvironment = new NothingEnvironment();
        Environment frontProjectilesEnvironment = new FrontProjectilesEnvironment(1);
        return nothingEnvironment;
    }
}