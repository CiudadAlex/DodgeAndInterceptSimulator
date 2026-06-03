package org.leviatanplatform.dodgeandinterceptorsimulator.visualizer;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.Player;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DodgeAndInterceptorVisualizer {

    private final PixelCanvas pixelCanvas;

    private final Player player;
    private double time = 0;
    private final int w;
    private final int h;
    private final double timeDelta;
    private final int sleepMillis;
    private int millisWaitBetweenMovements = 500;
    private JFrame frame;

    public DodgeAndInterceptorVisualizer(Player player, int w, int h, int pixelScale, double timeDelta, int sleepMillis) {

        this.player = player;
        this.w = w;
        this.h = h;
        this.timeDelta = timeDelta;
        this.sleepMillis = sleepMillis;

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
        startSimulation();
    }

    private void startSimulation() {

        Thread thread = new Thread(() -> {

            boolean finished = false;
            while(!finished) {
                finished = simulationLoopReturnFinished();
                refreshAll();
            }
        });

        thread.start();
    }

    private boolean simulationLoopReturnFinished() {

        time = time + timeDelta;
        sleep();

        return false;
    }

    private void sleep() {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshAll() {
        paintCanvas();
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

        MobileObject self = player.getSelf(time);

        if (self == null) {
            frame.setTitle("STOP !!!!");
            return;
        }

        pixelCanvas.reset();

        long timeLong = Math.round(100 * time);
        frame.setTitle("Dodge and Intercept simulator. Time " + timeLong/100f);

        List<Projectile> shoots = player.getShoots();

        for (Projectile projectile : shoots) {
            paintMobileObject(projectile, Color.WHITE, time);
        }

        Environment environment = player.getEnvironment();
        StoppedObject target = player.getTarget();

        paintMobileObject(self, Color.GREEN, time);

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
