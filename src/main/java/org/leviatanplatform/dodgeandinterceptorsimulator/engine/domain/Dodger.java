package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Dodger implements MobileObject {

    private final Position positionAtTimeZero;
    private final List<DodgerSegment> listDodgerSegment;
    private final double radius;
    private final double segmentTime;

    public Dodger(Position positionAtTimeZero, double radius, double segmentTime, List<DodgerSegment> listDodgerSegment) {
        this.positionAtTimeZero = positionAtTimeZero;
        this.radius = radius;
        this.segmentTime = segmentTime;
        this.listDodgerSegment = new ArrayList<>(listDodgerSegment);
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Position getPosition(double time) {

        DodgerSegment dodgerSegment = getDodgerSegment(time);

        if (dodgerSegment != null) {
            return dodgerSegment.getPosition(time);
        }

        return positionAtTimeZero;
    }

    public DodgerSegment getDodgerSegment(double time) {

        for (DodgerSegment dodgerSegment : listDodgerSegment) {
            if (time > dodgerSegment.getInitialTime() && time < dodgerSegment.getFinalTime()) {
                return dodgerSegment;
            }
        }

        return null;
    }

    public DodgerSegment getLastDodgerSegment() {

        int indexLast = listDodgerSegment.size() - 1;
        if (indexLast == -1) {
            return null;
        }

        return listDodgerSegment.get(indexLast);
    }

    public void changeDirection(double time, Movement movement, double velocityModule) {

        DodgerSegment dodgerSegment = getLastDodgerSegment();
        Position initialPosition;

        if (dodgerSegment == null) {
            initialPosition = positionAtTimeZero;
        } else {
            initialPosition = dodgerSegment.getPosition(time);
        }

        Velocity velocity = Velocity.calculateVelocity(movement, velocityModule);
        listDodgerSegment.add(new DodgerSegment(initialPosition, time, velocity, radius, movement, segmentTime));
    }

    public Dodger cloneDodgerWithChangeDirection(double time, Movement movement, double velocityModule, double timeStepDodger) {
        Dodger newDodger = new Dodger(positionAtTimeZero, radius, timeStepDodger, listDodgerSegment);
        newDodger.changeDirection(time, movement, velocityModule);
        return newDodger;
    }

    public List<DodgerSegment> getListDodgerSegment() {
        return listDodgerSegment;
    }
}
