package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class NoCollisionPathFinder {

    public static <T extends MobileObject> List<MovementAndTime> findNoCollisionPath(Position origin, double radius, double securityMargin, List<T> listMobileObject, Position target, int precision, double timeStepDodger, double velocityModule) {

        Dodger dodger = new Dodger(origin.clonePosition(), Velocity.ZERO, radius + securityMargin, timeStepDodger);
        List<Dodger> listDodgerLastIteration = List.of(dodger);
        StoppedObject stoppedTarget = new StoppedObject(target, 0);
        double currentTime = 0;

        while (true) {

            List<Dodger> listDodgerIteration = getNewListDodgerOfTimeStep(listDodgerLastIteration, currentTime, listMobileObject, precision, timeStepDodger, velocityModule);

            Dodger dodgerInTarget = getDodgerThatHitsTheTarget(listDodgerIteration, stoppedTarget, currentTime, precision, timeStepDodger);

            if (dodgerInTarget != null) {
                return dodgerInTarget.getListMovementAndTime();
            }

            listDodgerLastIteration = listDodgerIteration;
            currentTime = currentTime + timeStepDodger;
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

            Dodger newDodger = dodger.cloneDodgerWithChangeDirection(currentTime, movement, velocityModule, timeStepDodger);

            boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(dodger, listMobileObject, currentTime, currentTime + timeStepDodger, precision);

            if (!isThereCollision) {
                listDodgerNextTimeStep.add(newDodger);
            }

        }

        return listDodgerNextTimeStep;
    }
}
