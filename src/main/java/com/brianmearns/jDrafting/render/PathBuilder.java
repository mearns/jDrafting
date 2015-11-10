package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Reflexive;

public interface PathBuilder<R extends Renderer> {

    public enum SweepDirection {
        Clockwise, CounterClockwise;
    }

    /**
     * Lift the pen and move it to the given location, specified in absolute coordinates.
     *
     * @param x The absolute X coordinate to move to.
     * @param y The absolute Y coordinate to move to.
     */
    @NotNull
    @Reflexive
r   public PathBuilder<R> moveTo(double x, double y);

    /**
     * Lift the pen and move it by the specified offsets.
     *
     * @param dx The distance to move in the X direction from the current pen location.
     * @param dy The distance to move in the Y direction from the current pen location.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> move(double dx, double dy);

    /**
     * Draw a line directly from the current location to the specified location.
     *
     * @param x The absolute X coordinate to draw a line to.
     * @param y The absolute Y coordinate to draw a line to.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> lineTo(double x, double y);

    /**
     * Draw a line directly from the current location to the location specified
     * by the given <em>relative</em> offsets from the current position.
     *
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> line(double dx, double dy);

    /**
     * Draw a quadratic (three-point) Bezier curve from the current location to the
     * specified location.
     *
     * @param cx The absolute X coordinate of the control point.
     * @param cy The absolute Y coordinate of the control point.
     * @param x The absolute X coordinate of the end point.
     * @param y The absolute Y coordinate of the end point.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> curveTo(double cx, double cy, double x, double y);

    /**
     * Like {@link #curveTo(double, double, double, double)} draws a quadratic Bezier
     * curve, but using all relative offsets from the current location.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> curve(double dcx, double dcy, double dx, double dy);

    /**
     * Draw a cubic (four-point) Bezier curve from the current location to the
     * specified location.
     *
     * @param c1x The absolute X coordinate of the first control point.
     * @param c1y The absolute Y coordinate of the first control point.
     * @param c2x The absolute X coordinate of the second control point.
     * @param c2y The absolute Y coordinate of the second control point.
     * @param x The absolute X coordinate of the end point.
     * @param y The absolute Y coordinate of the end point.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> curveTo(double c1x, double c1y, double c2x, double c2y, double x, double y);

    /**
     * Like {@link #curveTo(double, double, double, double, double, double)} draws a cubic Bezier
     * curve, but using all relative offsets from the current location.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> curve(double dc1x, double dc1y, double dc2x, double dc2y, double dx, double dy);

    /**
     * Draws an elliptic curve from the current location to the given point.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> arcTo(double radiusX, double radiusY, double xAxisRotate, boolean largestArc, SweepDirection sweep, double x, double y);

    /**
     * Draws a straight line directly from the current location to the starting point of the curve.
     */
    @NotNull
    @Reflexive
    public PathBuilder<R> closePath();

    /**
     * Ends the current path, returning the {@link Renderer} from which it originates.
     */
    @NotNull
    @Reflexive
    public R endPath();

    /**
     * Convenience method for closing and ending the path. This is functionally equivalent
     * to calling {@link #closePath()} followed by {@link #endPath()}.
     */
    @NotNull
    @Reflexive
    public R closeAndEndPath();


}

