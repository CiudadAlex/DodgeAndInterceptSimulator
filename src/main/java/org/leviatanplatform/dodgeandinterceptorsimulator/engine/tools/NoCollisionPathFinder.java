package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class NoCollisionPathFinder {

    public static <T extends MobileObject> List<DodgerSegment> findNoCollisionPath(Position origin, double radius, double securityMargin, List<T> listMobileObject, Position target, int precision, double timeStepDodger, double velocityModule, int maxProcessableDodgers) {

        Dodger dodger = new Dodger(origin.clonePosition(), radius + securityMargin, timeStepDodger, new ArrayList<>());
        List<Dodger> listDodgerLastIteration = List.of(dodger);
        StoppedObject stoppedTarget = new StoppedObject(target, 0);
        double currentTime = 0;

        while (!listDodgerLastIteration.isEmpty()) {

            List<Dodger> listDodgerIteration = getNewListDodgerOfTimeStep(listDodgerLastIteration, currentTime, listMobileObject, precision, timeStepDodger, velocityModule);
            listDodgerIteration = filterListDodger(listDodgerIteration, target, maxProcessableDodgers);
            printStatistics(listDodgerIteration, target);

            Dodger dodgerInTarget = getDodgerThatHitsTheTarget(listDodgerIteration, stoppedTarget, currentTime, precision, timeStepDodger);

            if (dodgerInTarget != null) {
                return dodgerInTarget.getListDodgerSegment();
            }

            listDodgerLastIteration = listDodgerIteration;
            currentTime = currentTime + timeStepDodger;
        }

        throw new RuntimeException("No path found");
    }

    private static void printStatistics(List<Dodger> listDodgerIteration, Position target) {

        DistanceStatistics distanceStatistics = getDistanceStatistics(listDodgerIteration, target);

        if (distanceStatistics == null) {
            return;
        }

        System.out.println("Number of dodgers: " + listDodgerIteration.size() + "  |  maxDistance: " + distanceStatistics.getMaxDistance() + "  |  minDistance: " + distanceStatistics.getMinDistance());
    }

    private static List<Dodger> filterListDodger(List<Dodger> listDodgerIteration, Position target, int maxProcessableDodgers) {

        List<Dodger> listDodgerFiltered = new ArrayList<>(listDodgerIteration);

        while (listDodgerFiltered.size() > maxProcessableDodgers) {

            DistanceStatistics distanceStatistics = getDistanceStatistics(listDodgerFiltered, target);
            double meanDistance = distanceStatistics.getMeanDistance();
            listDodgerFiltered = listDodgerFiltered.stream().filter(dodger -> getDistance(dodger, target) < meanDistance).toList();
        }

        return listDodgerFiltered;
    }

    private static double getDistance(Dodger dodger, Position target) {
        DodgerSegment dodgerSegment = dodger.getLastDodgerSegment();
        Position finalPosition = dodgerSegment.getFinalPosition();
        return MobileObjectCollisionDetector.calculateDistance(finalPosition, target);
    }

    private static DistanceStatistics getDistanceStatistics(List<Dodger> listDodgerIteration, Position target) {

        if (listDodgerIteration.size() == 0) {
            return null;
        }

        double maxDistance = Double.NEGATIVE_INFINITY;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Dodger dodger : listDodgerIteration) {
            double distance = getDistance(dodger, target);

            if (distance > maxDistance) {
                maxDistance = distance;
            }

            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return new DistanceStatistics(maxDistance, minDistance);
    }

    private static Dodger getDodgerThatHitsTheTarget(List<Dodger> listDodger, StoppedObject stoppedTarget, double currentTime, int precision, double timeStep) {

        for (Dodger dodger : listDodger) {

            boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(dodger, List.of(stoppedTarget), currentTime, currentTime + timeStep, precision);

            if (isThereCollision) {
                return dodger;
            }
        }

        return null;
    }

    private static <T extends MobileObject> List<Dodger> getNewListDodgerOfTimeStep(List<Dodger> listDodger, double currentTime, List<T> listMobileObject, int precision, double timeStepDodger, double velocityModule) {

        List<Dodger> listDodgerIteration = new ArrayList<>();

        for (Dodger dodger : listDodger) {
            List<Dodger> listDodgerNextTimeStep = getNewListDodgerOfTimeStep(dodger, currentTime, listMobileObject, precision, timeStepDodger, velocityModule);
            listDodgerIteration.addAll(listDodgerNextTimeStep);
        }

        return listDodgerIteration;
    }

    private static <T extends MobileObject> List<Dodger> getNewListDodgerOfTimeStep(Dodger dodger, double currentTime, List<T> listMobileObject, int precision, double timeStepDodger, double velocityModule) {

        List<Dodger> listDodgerNextTimeStep = new ArrayList<>();

        for (Movement movement : Movement.values()) {

            Dodger newDodger = dodger.cloneDodgerWithChangeDirection(currentTime, movement, velocityModule);

            boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(newDodger, listMobileObject, currentTime, currentTime + timeStepDodger, precision);

            if (!isThereCollision) {
                listDodgerNextTimeStep.add(newDodger);
            }

        }

        return listDodgerNextTimeStep;
    }
}
