package org.leviatanplatform.dodgeandinterceptorsimulator.visualizer;

import javax.swing.*;
import java.awt.*;

public class DodgeAndInterceptorVisualizer {

    private final PixelCanvas pixelCanvas;

    private final int w;
    private final int h;
    private int millisWaitBetweenMovements = 500;
    private JFrame frame;

    public DodgeAndInterceptorVisualizer(int w, int h, int pixelScale) {

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

        Color color = Color.RED;
        // FIXME finish
        //pixelCanvas.setCircle(Position center, double radius, Color color);

        pixelCanvas.repaint();
    }

    public void accelerateMovement(float acceleratingFactor) {
        millisWaitBetweenMovements = (int) Math.ceil(millisWaitBetweenMovements/acceleratingFactor);
    }

    public void zoom(int pixelsToAdd) {
        pixelCanvas.addToPixelScale(pixelsToAdd);
    }

}
