package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public interface MobileObject {

    double getRadius();

    Position getPosition(double time);

    boolean isDestroyed();
}
