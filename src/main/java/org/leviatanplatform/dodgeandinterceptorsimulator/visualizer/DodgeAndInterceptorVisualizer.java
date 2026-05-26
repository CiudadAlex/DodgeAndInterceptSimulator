package org.leviatanplatform.dodgeandinterceptorsimulator.visualizer;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.World;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import javax.swing.*;
import java.awt.*;

public class DodgeAndInterceptorVisualizer {

    private final PixelCanvas pixelCanvas;

    private final World world;
    private double time = 0;
    private final int w;
    private final int h;
    private int millisWaitBetweenMovements = 500;
    private JFrame frame;

    public DodgeAndInterceptorVisualizer(World world, int w, int h, int pixelScale) {

        this.world = world;
        this.w = w;
        this.h = h;

        this.pixelCanvas = new PixelCanvas(w, h, pixelScale);
    }

    public void show() {

        if (frame == null) {

            frame = new JFrame("Dodge and Interceptor Simulator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(w + 30, h + 50);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.addKeyListener(new CommandListener(this));

            frame.add(pixelCanvas);
        }

        paintCanvas();
    }

    // FIXME init a thread that changes time and refreshes UI

    private void refreshAll() {
        paintCanvas();
    }

    public void resetCanvas() {
        SwingUtilities.invokeLater(() -> {
            pixelCanvas.reset();
        });
    }

    public void paintCanvas() {
        SwingUtilities.invokeLater(() -> {
            innerPaintCanvas();
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    public void innerPaintCanvas() {

        frame.setTitle("Dodge and Intercept simulator");

        Dodger dodger = world.getDodger();
        Environment environment = world.getEnvironment();
        StoppedObject target = world.getTarget();

        paintMobileObject(dodger, Color.GREEN, time);

        for (Projectile projectile : environment.getProjectiles()) {
            paintMobileObject(projectile, Color.YELLOW, time);
        }

        paintMobileObject(target, Color.RED, time);

        pixelCanvas.repaint();
    }

    public void paintMobileObject(MobileObject mobileObject, Color color, double time) {
        pixelCanvas.setCircle(mobileObject.getPosition(time), mobileObject.getRadius(), color);
    }

    public void accelerateMovement(float acceleratingFactor) {
        millisWaitBetweenMovements = (int) Math.ceil(millisWaitBetweenMovements/acceleratingFactor);
    }

    public void zoom(int pixelsToAdd) {
        pixelCanvas.addToPixelScale(pixelsToAdd);
    }

}
