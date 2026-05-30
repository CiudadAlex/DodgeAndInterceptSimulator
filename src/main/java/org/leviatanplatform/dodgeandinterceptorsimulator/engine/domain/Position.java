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

    public Position cloneAdding(double addToX, double addToY) {
        return new Position(x + addToX, y + addToY);
    }

    public Position clonePosition() {
        return new Position(x, y);
    }

    public PositionInfo clonePositionInfo() {
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "( " + x + " , " + y + " )";
    }
}
