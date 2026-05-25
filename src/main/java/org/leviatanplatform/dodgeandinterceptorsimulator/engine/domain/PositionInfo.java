package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public interface PositionInfo {

    double getX();

    double getY();

    PositionInfo clonePositionInfo();
}
