package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class PolarCoordinatePoint extends AbstractPoint {

    @NotNull
    private final Scalar thetaScalar;

    @NotNull
    private final Scalar radiusScalar;

    public PolarCoordinatePoint(@NotNull Scalar thetaScalar, @NotNull Scalar radiusScalar) {
        this.thetaScalar = thetaScalar;
        this.radiusScalar = radiusScalar;
    }

    @NotNull
    @Contract(pure=true)
    public static Point create(@NotNull Scalar theta, @NotNull Scalar radius) {
        return new PolarCoordinatePoint(theta, radius);
    }

    @Override
    @NotNull
    public Scalar getThetaScalar() {
        return thetaScalar;
    }

    @Override
    @NotNull
    public Scalar getRadiusScalar() {
        return radiusScalar;
    }

    /**
     * Resolves the current location of the point and returns the X coordinate.
     */
    @Override
    public double getX() {
        return radiusScalar.getValue() * Math.cos(thetaScalar.getValue());
    }

    /**
     * Resolves the current location of the point and returns the Y coordinate.
     */
    @Override
    public double getY() {
        return radiusScalar.getValue() * Math.sin(thetaScalar.getValue());
    }
}
