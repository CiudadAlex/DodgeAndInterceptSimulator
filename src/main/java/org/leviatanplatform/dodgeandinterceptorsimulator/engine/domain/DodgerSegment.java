package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class DodgerSegment implements MobileObject {

    private final Position initialPosition;
    private final double initialTime;
    private final Velocity velocity;
    private final double radius;
    private final Movement movement;
    private final Position finalPosition;
    private final double segmentTime;

    public DodgerSegment(Position initialPosition, double initialTime, Velocity velocity, double radius, Movement movement, double segmentTime) {
        this.initialPosition = initialPosition;
        this.initialTime = initialTime;
        this.velocity = velocity;
        this.radius = radius;
        this.movement = movement;
        this.finalPosition = velocity.getFinalPosition(initialPosition, segmentTime);
        this.segmentTime = segmentTime;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public double getInitialTime() {
        return initialTime;
    }

    public double getFinalTime() {
        return initialTime + segmentTime;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Movement getMovement() {
        return movement;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public double getSegmentTime() {
        return segmentTime;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Position getPosition(double time) {
        double elapsedTime = time - initialTime;
        return velocity.getFinalPosition(initialPosition, elapsedTime);
    }
}
