package org.leviatanplatform.dodgeandinterceptorsimulator.engine.tools;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.MobileObject;
import org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain.Position;

public class MobileObjectCollisionDetector {

    public static boolean isThereCollision(MobileObject mobileObject1, MobileObject mobileObject2, double timeInitial, double timeFinal, int precision) {

        double sumRadius = mobileObject1.getRadius() + mobileObject2.getRadius();
        double timeStep = (timeFinal - timeInitial) / precision;

        for (int i = 0; i <= precision; i++) {
            double time = timeInitial + i * timeStep;
            Position position1 = mobileObject1.getPosition(time);
            Position position2 = mobileObject2.getPosition(time);
            double distance = calculateDistance(position1, position2);

            if (distance < sumRadius) {
                return true;
            }
        }

        return false;
    }

    public static double calculateDistance(Position position1, Position position2) {

        double x1 = position1.getX();
        double y1 = position1.getY();

        double x2 = position2.getX();
        double y2 = position2.getY();

        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
