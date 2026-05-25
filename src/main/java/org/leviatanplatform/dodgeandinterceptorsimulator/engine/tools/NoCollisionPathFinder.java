package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Dodger;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.MobileObject;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Movement;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;

import java.util.List;

public class NoCollisionPathFinder {

    private static final double TIME_STEP = 1;

    public static <T extends MobileObject> List<Movement> findNoCollisionPath(Dodger dodger, List<T> listMobileObject, Position target, int precision) {

        while (true) {

            Position currentPosition = dodger.getInitialPosition();
            double currentTime = 0;


            boolean isThereCollision = MobileObjectCollisionDetector.isThereCollision(dodger, listMobileObject, currentTime, currentTime + TIME_STEP, precision);


            //
        }
    }
}
