package org.leviatanplatform.dodgeandinterceptorsimulator.engine.math;

import org.leviatanplatform.dodgeandinterceptorsimulator.engine.math.model.Range;

import java.util.function.Function;

public class RootFinder {


    public static Double findRootValueForX(Function<Double, Double> function, Range rangeX, int searchGranularity, Function<Double, Boolean> acceptableX, double maxYDeviationFromZero) {

        Range currentRangeX = rangeX;

        while (true) {

            currentRangeX = reduceRangeOfChangingSign(function, currentRangeX, searchGranularity, acceptableX);

            if (currentRangeX == null) {
                // No root found in range
                return null;
            }

            Double goodEnoughX = getGoodEnoughX(function, currentRangeX, maxYDeviationFromZero);

            if (goodEnoughX != null) {
                return goodEnoughX;
            }
        }
    }

    private static Double getGoodEnoughX(Function<Double, Double> function, Range rangeX, double maxYDeviationFromZero) {

        double xMin = rangeX.getMin();
        double xMax = rangeX.getMax();
        double xMean = (xMax + xMin) / 2;

        double yMin = function.apply(xMin);
        double yMax = function.apply(xMax);
        double yMean = function.apply(xMean);

        if (yMean < maxYDeviationFromZero) {
            return xMean;
        }

        if (yMax < maxYDeviationFromZero) {
            return xMax;
        }

        if (yMin < maxYDeviationFromZero) {
            return xMin;
        }

        return null;
    }

    private static Range reduceRangeOfChangingSign(Function<Double, Double> function, Range rangeX, int searchGranularity, Function<Double, Boolean> acceptableX) {

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
}
