package org.leviatanplatform.dodgeandinterceptorsimulator.visualizer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandListener extends KeyAdapter {

    private DodgeAndInterceptorVisualizer dodgeAndInterceptorVisualizer;

    public CommandListener(DodgeAndInterceptorVisualizer roombaVisualizer) {
        this.dodgeAndInterceptorVisualizer = roombaVisualizer;
    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> up();
            case KeyEvent.VK_DOWN -> down();
            case KeyEvent.VK_LEFT -> left();
            case KeyEvent.VK_RIGHT -> right();
            case KeyEvent.VK_S -> start();
            case KeyEvent.VK_0 -> accelerateExploring();
            case KeyEvent.VK_9 -> decelerateExploring();
            case KeyEvent.VK_1 -> zoomOut();
            case KeyEvent.VK_2 -> zoomIn();
            case KeyEvent.VK_H -> help();
        }
    }

    private void up() {
    }

    private void down() {
    }

    private void left() {
    }

    private void right() {
    }

    private void zoomOut() {
        dodgeAndInterceptorVisualizer.zoom(-1);
    }

    private void zoomIn() {
        dodgeAndInterceptorVisualizer.zoom(1);
    }

    private void start() {
    }

    private void accelerateExploring() {
        dodgeAndInterceptorVisualizer.accelerateMovement(2);
    }

    private void decelerateExploring() {
        dodgeAndInterceptorVisualizer.accelerateMovement(0.5f);
    }

    private void help() {

        System.out.println("====================================================");
        System.out.println();
        System.out.println("Useful keys:");
        System.out.println(" - Arrows to navigate the roomba");
        System.out.println(" - S: Start exploring");
        System.out.println(" - 0: Accelerate exploring");
        System.out.println(" - 9: Decelerate exploring");
        System.out.println(" - H: help");
        System.out.println();
        System.out.println("====================================================");
    }

}
