package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class Position implements PositionInfo {

    private static final double SQRT_2 = Math.sqrt(2);
    private double x;
    private double y;

    public Position(PositionInfo positionInfo) {
        this(positionInfo.getX(), positionInfo.getY());
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(Movement movement) {
        move(movement, 1);
    }

    public void move(Movement movement, double numberToAdd) {
        switch (movement) {
            case UP -> y = y + numberToAdd;
            case UP_RIGHT -> {
                x = x + SQRT_2 * numberToAdd;
                y = y + SQRT_2 * numberToAdd;
            }
            case RIGHT -> x = x + numberToAdd;
            case DOWN_RIGHT -> {
                x = x + SQRT_2 * numberToAdd;
                y = y - SQRT_2 * numberToAdd;
            }
            case DOWN -> y = y - numberToAdd;
            case DOWN_LEFT -> {
                x = x - SQRT_2 * numberToAdd;
                y = y - SQRT_2 * numberToAdd;
            }
            case LEFT -> x = x - numberToAdd;
            case UP_LEFT -> {
                x = x - SQRT_2 * numberToAdd;
                y = y + SQRT_2 * numberToAdd;
            }
        }
    }

    public Position cloneAdding(double addToX, double addToY) {
        return new Position(x + addToX, y + addToY);
    }

    public Position clonePosition() {
        return new Position(x, y);
    }

    public PositionInfo clonePositionInfo() {
        return new Position(x, y);
    }
}
