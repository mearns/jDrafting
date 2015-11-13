package com.brianmearns.jDrafting.geom;

import javax.validation.constraints.NotNull;

/**
 * Represents a location on a two dimensional plane.
 * A Point object is not necessarily at a fixed location, it may be derived from
 * other points or sources of points, which may change.
 */
public interface Point {

    /**
     * Resolves the current location of the point and returns the X coordinate.
     */
    double getX();

    /**
     * Resolves the current location of the point and returns the Y coordinate.
     */
    double getY();

    /**
     * Returns the distance between this point and the given point.
     */
    double distance(@NotNull Point from);

    /**
     * Returns the square of the distance between this point and the next.
     */
    double distanceSquared(@NotNull Point from);

    /**
     * Resolves the current location of the point and returns a new Point object
     * fixed to this position.
     */
    @NotNull
    Point stamp();

    /**
     * Returns a new point which is at a fixed Cartesian offset from this point.
     */
    @NotNull
    Point translate(double x, double y);

    /**
     * Returns a new point at a fixed dilation from the given reference point.
     */
    @NotNull
    Point dilate(double scale, @NotNull Point ref);

    @NotNull
    Point dilate(double scale);

    /**
     * Returns a new point at a fixed rotation from the given reference point.
     */
    @NotNull
    Point rotate(double degrees, @NotNull Point pivot);

    @NotNull
    Point rotate(double degrees);
}

