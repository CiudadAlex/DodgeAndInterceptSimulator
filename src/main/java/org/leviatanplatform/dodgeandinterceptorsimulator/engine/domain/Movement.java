package org.leviatanplatform.dodgeandinterceptorsimulator.engine.domain;

import java.util.List;

public enum Movement {
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT,
    UP_LEFT;

    private static boolean limitValues = false;

    public static void setLimitValues(boolean limitValues) {
        Movement.limitValues = limitValues;
    }

    public static List<Movement> getValues() {

        if (limitValues) {
            return List.of(UP, RIGHT, DOWN, LEFT);
        }

        return List.of(values());
    }


}
