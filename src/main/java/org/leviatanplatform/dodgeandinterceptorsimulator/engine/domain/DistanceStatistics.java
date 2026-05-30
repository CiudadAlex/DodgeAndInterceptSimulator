package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

public class DistanceStatistics {

    private final double maxDistance;
    private final double minDistance;

    public DistanceStatistics(double maxDistance, double minDistance) {
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public double getMeanDistance() {
        return (maxDistance + minDistance) / 2;
    }
}
