package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class NoCollisionPathFinder {

    public static <T extends MobileObject> List<Movement> findNoCollisionPath(Position origin, double radius, List<T> listMobileObject, Position target, int precision, double timeStep) {

        Dodger dodger = new Dodger(origin.clonePosition(), Velocity.ZERO, radius);
        List<Dodger> listDodgerLastIteration = List.of(dodger);
        StoppedObject stoppedTarget = new StoppedObject(target, radius);
        double currentTime = 0;

        while (true) {

            List<Dodger> listDodgerIteration = getNewListDodgerOfTimeStep(listDodgerLastIteration, currentTime, listMobileObject, precision, timeStep);

            Dodger dodgerInTarget = getDodgerThatHitsTheTarget(listDodgerIteration, stoppedTarget, currentTime, precision, timeStep);

            if (dodgerInTarget != null) {
                return dodgerInTarget.getListMovement();
            }

            listDodgerLastIteration = listDodgerIteration;
            currentTime = currentTime + timeStep;
        }
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

    private static <T extends MobileObject> List<Dodger> getNewListDodgerOfTimeStep(List<Dodger> listDodger, double currentTime, List<T> listMobileObject, int precision, double timeStep) {

        List<Dodger> listDodgerIteration = new ArrayList<>();

        for (Dodger dodger : listDodger) {
            List<Dodger> listDodgerNextTimeStep = getNewListDodgerOfTimeStep(dodger, currentTime, listMobileObject, precision, timeStep);
            listDodgerIteration.addAll(listDodgerNextTimeStep);
        }

        return listDodgerIteration;
    }

    private static <T extends MobileObject> List<Dodger> getNewListDodgerOfTimeStep(Dodger dodger, double currentTime, List<T> listMobileObject, int precision, double timeStep) {

        List<Dodger> listDodgerNextTimeStep = new ArrayList<>();

        for (Movement movement : Movement.values()) {

            Dodger newDodger = dodger.cloneDodgerWithChangeDirection(currentTime, movement);

            boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(dodger, listMobileObject, currentTime, currentTime + timeStep, precision);

            if (!isThereCollision) {
                listDodgerNextTimeStep.add(newDodger);
            }

        }

        return listDodgerNextTimeStep;
    }
}
