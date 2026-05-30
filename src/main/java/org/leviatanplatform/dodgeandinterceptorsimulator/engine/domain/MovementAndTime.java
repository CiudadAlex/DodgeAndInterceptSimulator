package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class MovementAndTime {

    private final Movement movement;
    private final double time;

    public MovementAndTime(Movement movement, double time) {
        this.movement = movement;
        this.time = time;
    }

    public Movement getMovement() {
        return movement;
    }

    public double getTime() {
        return time;
    }
}
