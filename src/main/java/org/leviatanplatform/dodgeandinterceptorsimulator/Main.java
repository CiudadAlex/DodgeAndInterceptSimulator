package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.FrontProjectilesEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.NothingEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.DodgeAndInterceptorVisualizer;

import javax.swing.*;

public class Main {

    // FIXME test shooter

    public static void main(String[] args) {

        boolean autoCalculateStrategy = true;
        boolean limitMovementValues = true;

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
        double velocityShootModule = 1;
        double radiusShoot = 1;

        Movement.setLimitValues(limitMovementValues);

        Position initialPositionDodger = new Position(-4, 0);
        Environment env = getEnvironment();
        StoppedObject target = new StoppedObject(new Position(4, 0), radiusTarget);
        Player player = PlayerBuilder.buildPlayer(PlayerBuilder.Action.SHOOT, env, target, initialPositionDodger, radiusDodger, timeStepDodger, velocityDodgerModule, securityMargin,
                collisionPrecision, maxProcessableDodgers, velocityShootModule, radiusShoot);

        SwingUtilities.invokeLater(() -> {
            DodgeAndInterceptorVisualizer visualizer = new DodgeAndInterceptorVisualizer(player, w, h, pixelScale, visualizerTimeDelta, visualizerSleepMillis);
            visualizer.show();
        });
    }

    private static Environment getEnvironment() {
        Environment nothingEnvironment = new NothingEnvironment();
        Environment frontProjectilesEnvironment = new FrontProjectilesEnvironment(1, 4, 3, 3);
        return frontProjectilesEnvironment;
    }
}