package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public interface MobileObject {

    Position getInitialPosition();

    Velocity getVelocity();

    double getRadius();

    Position getPosition(double time);
}
