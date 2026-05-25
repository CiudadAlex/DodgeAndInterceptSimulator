package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.*;

import java.util.ArrayList;
import java.util.List;

public class NoCollisionPathFinder {

    private static final double TIME_STEP = 1;

    public static <T extends MobileObject> List<Movement> findNoCollisionPath(Position origin, double radius, List<T> listMobileObject, Position target, int precision) {

        Dodger dodger = new Dodger(origin.clonePosition(), Velocity.ZERO, radius);

        while (true) {

            double currentTime = 0;

            List<Dodger> listDodgerIteration = new ArrayList<>();

            for (Movement movement : Movement.values()) {

                Dodger newDodger = dodger.cloneDodgerWithChangeDirection(currentTime, movement);

                boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(dodger, listMobileObject, currentTime, currentTime + TIME_STEP, precision);
                if (!isThereCollision) {
                    listDodgerIteration.add(newDodger);
                }

            }

        }
    }
}
