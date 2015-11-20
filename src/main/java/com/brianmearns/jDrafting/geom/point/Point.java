package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.Scalar;

import javax.validation.constraints.NotNull;

/**
 * Represents a location on a two dimensional plane.
 * A Point object is not necessarily at a fixed location, it may be derived from
 * other points or sources of points, which may change.
 */
public interface Point {

    @NotNull
    static Point origin() {
        return FixedPoint.origin();
    }

    /**
     * Resolves the current location of the point and returns the X coordinate.
     */
    double getX();

    /**
     * Resolves the current location of the point and returns the Y coordinate.
     */
    double getY();

    /**
     * Determines the polar coordinates of the point and returns the angle in radians (counter clockwise of the
     * positive X axis).
     */
    @NotNull
    Scalar getThetaScalar();

    /**
     * Determines the polar coordinates of the point and returns the radius of this point from the origin.
     */
    @NotNull
    Scalar getRadiusScalar();

    /**
     * Returns the distance between this point and the given point.
     */
    Scalar distance(@NotNull Point from);

    /**
     * Returns the square of the distance between this point and the next. If this is what you want, it's more
     * efficient than calling {@link #distance(Point)} and squaring the result.
     */
    Scalar distanceSquared(@NotNull Point from);

    /**
     * Resolves the current location of the point and returns a new Point object
     * fixed to this position.
     */
    @NotNull
    Point fix();

    /**
     * Returns a Point which is at a specified Cartesian offset from this point.
     */
    @NotNull
    Point translate(@NotNull Scalar x, @NotNull Scalar y);

    /**
     * Returns a Point at the specified dilation from the given reference point.
     */
    @NotNull
    Point dilate(@NotNull Scalar scale, @NotNull Point ref);

    /**
     * Return a Point at the specified dilation from the origin.
     */
    @NotNull
    Point dilate(@NotNull Scalar scale);

    /**
     * Returns a Point at a specified rotation from the given reference point.
     */
    @NotNull
    Point rotate(@NotNull Scalar degrees, @NotNull Point pivot);

    @NotNull
    Point rotate(@NotNull Scalar degrees);

    /**
     * Returns a {@link Scalar} which is backed by the X coordinate of this Point.
     */
    @NotNull
    Scalar getXScalar();

    /**
     * Returns a {@link Scalar} which is backed by the Y coordinate of this Point.
     */
    @NotNull
    Scalar getYScalar();

    @NotNull
    Scalar getDeltaXFrom(@NotNull Point from);

    @NotNull
    Scalar getDeltaYFrom(@NotNull Point from);
}

