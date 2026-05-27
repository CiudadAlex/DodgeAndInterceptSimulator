package org.leviatanplatform.dodgeandinterceptorsimulator.visualizer;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;
import org.leviatanplatform.dodgeandinterceptorsimulator.visualizer.model.PixelCoordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelCanvas extends JPanel {

    private BufferedImage canvas;
    private int pixelScale;
    private int w;
    private int h;

    public PixelCanvas(int w, int h, int pixelScale) {
        this.w = w;
        this.h = h;
        this.pixelScale = pixelScale;
        this.canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    public void setCircle(Position center, double radius, Color color) {

        Graphics2D g = this.canvas.createGraphics();

        Position upperLeftCorner = center.cloneAdding(-radius, radius);
        PixelCoordinates pixelCoordinates = transformCoordinates(upperLeftCorner);
        int px = pixelCoordinates.getX();
        int py = pixelCoordinates.getY();
        int pdiameter = transformLength(2 * radius);

        g.setColor(color);
        g.fillOval(px, py, pdiameter, pdiameter);
    }

    private int transformLength(double length) {
        return (int) Math.round(length * pixelScale);
    }

    private PixelCoordinates transformCoordinates(Position position) {
        double x = position.getX();
        double y = position.getY();

        int px = (int) Math.round(  x * pixelScale + w / 2.0);
        int py = (int) Math.round(- y * pixelScale + h / 2.0);

        return new PixelCoordinates(px, py);
    }

    public void reset() {

        Graphics2D g = this.canvas.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
    }

    public void addToPixelScale(int pixelsToAdd) {
        this.pixelScale = this.pixelScale + pixelsToAdd;

        if (this.pixelScale < 1) {
            this.pixelScale = 1;
        }
    }

    public int getPixelScale() {
        return pixelScale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
