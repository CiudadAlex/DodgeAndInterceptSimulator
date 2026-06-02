package org.leviatanplatform.dodgeandinterceptorsimulator.engine.math;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.math.model.Range;

import java.util.function.Function;

public class RootFinder {

    private static Range buildFunctionToFindRootVx(Function<Double, Double> function, double minX, double maxX,
                                                   double maxDeviationFromZero, int searchGranularity, Function<Double, Boolean> acceptableX) {

        double step = (maxX - minX) / searchGranularity;
        Boolean signPositive = null;
        Double lastX = null;

        for (int i = 0; i <= searchGranularity; i++) {

            double x = minX + i * step;
            boolean acceptable = acceptableX.apply(x);

            if (!acceptable) {
                continue;
            }

            double y = function.apply(x);
            boolean newSignPositive = y > 0;

            if (signPositive != null) {
                boolean changeOfSign = signPositive ^ newSignPositive;
                if (changeOfSign) {
                    return new Range(lastX, x);
                }
            }

            signPositive = newSignPositive;
            lastX = x;
        }

        return null;
    }
}
