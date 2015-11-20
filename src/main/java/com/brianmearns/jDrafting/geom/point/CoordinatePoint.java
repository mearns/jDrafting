package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 * A coordinate point is a {@link Point} where the X and Y coordinates are explicitly given as {@link Scalar} objects.
 * Unlike a {@link FixedPoint}, this can still be dynamic because the underlying Scalar values are dynamic. However,
 * the specific scalar values that represent each coordinate cannot be reassigned.
 */
public class CoordinatePoint extends AbstractPoint {

    @NotNull
    private final Scalar xScalar;

    @NotNull
    private final Scalar yScalar;

    public CoordinatePoint(@NotNull Scalar xScalar, @NotNull Scalar yScalar) {
        this.xScalar = xScalar;
        this.yScalar = yScalar;
    }

    @NotNull
    @Contract(pure=true)
    public static Point create(@NotNull Scalar x, @NotNull Scalar y) {
        return new CoordinatePoint(x, y);
    }

    @Override
    @NotNull
    public Scalar getXScalar() {
        return xScalar;
    }


    @Override
    @NotNull
    public Scalar getYScalar() {
        return yScalar;
    }

    /**
     * Resolves the current location of the point and returns the X coordinate.
     */
    @Override
    public double getX() {
        return xScalar.getValue();
    }

    /**
     * Resolves the current location of the point and returns the Y coordinate.
     */
    @Override
    public double getY() {
        return yScalar.getValue();
    }
}
