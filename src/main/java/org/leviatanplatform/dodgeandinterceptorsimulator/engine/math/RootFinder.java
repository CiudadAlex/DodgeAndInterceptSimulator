package org.leviatanplatform.dodgeandinterceptorsimulator.engine.math;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.math.model.Range;

import java.util.function.Function;

public class RootFinder {

    // double maxYDeviationFromZero

    private static Range reduceRangeOfChangingSign(Function<Double, Double> function, Range rangeX,
                                                  int searchGranularity, Function<Double, Boolean> acceptableX) {

        double minX = rangeX.getMin();
        double maxX = rangeX.getMax();

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

    // FIXME finish, external loop until maxDeviationFromZero is reached
}
