package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

/**
 * A {@link com.brianmearns.jDrafting.geom.scalar.Scalar} representing the square of the Cartesian distance between two
 * {@link Point} objects.
 */
public class TwoPointDistanceSquaredScalar extends TwoPointScalar {

    @Nullable
    private static TwoPointCoordinateFunction<Double> valueFunc = null;

    public TwoPointDistanceSquaredScalar(@NotNull Point pt1, @NotNull Point pt2) {
        super(pt1, pt2);
    }

    @NotNull
    protected static TwoPointCoordinateFunction<Double> getValueFunc() {
        if(valueFunc == null) {
            valueFunc = (x1, y1, x2, y2) -> {
                final double dx = x1 - x2;
                final double dy = y1 - y2;
                return dx * dx + dy * dy;
            };
        }
        return valueFunc;
    }

    @NotNull
    @Contract(pure = true)
    public static Scalar create(@NotNull Point pt1, @NotNull Point pt2) {
        return new TwoPointDistanceSquaredScalar(pt1, pt2);
    }

    /**
     * This is the core function of this interface, it returns the <em>current</em> numeric value of the object.
     */
    @Override
    public double getValue() {
        return apply(getValueFunc());
    }

}
