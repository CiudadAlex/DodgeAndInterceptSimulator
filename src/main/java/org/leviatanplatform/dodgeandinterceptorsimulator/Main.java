package org.leviatanplatform.dodgeandinterceptorsimulator;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Environment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.FrontProjectilesEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.exampleenvironments.NothingEnvironment;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.DodgeAndInterceptorVisualizer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // FIXME finish
        int w = 1000;
        int h = 800;
        int pixelScale = 15;
        int radius = 3;

        Environment env = getEnvironment();

        SwingUtilities.invokeLater(() -> {
            DodgeAndInterceptorVisualizer visualizer = new DodgeAndInterceptorVisualizer(w, h, pixelScale);
            visualizer.show();
        });
    }

    private static Environment getEnvironment() {
        Environment nothingEnvironment = new NothingEnvironment();
        Environment frontProjectilesEnvironment = new FrontProjectilesEnvironment();
        return frontProjectilesEnvironment;
    }
}