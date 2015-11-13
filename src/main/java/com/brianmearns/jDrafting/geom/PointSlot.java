package com.brianmearns.jDrafting.geom;

import javax.validation.constraints.NotNull;

/**
 * A {@link Point} which is just a wrapper around another, settable, {@link Point}
 * object.
 */
public class PointSlot extends AbstractPoint {

    @NotNull
    private Point point;

    protected PointSlot(@NotNull Point point) {
        this.point = point;
    }

    /**
     * Factory method.
     */
    @NotNull
    public static PointSlot create(@NotNull Point point) {
        return new PointSlot(point);
    }

    @NotNull
    public Point getPoint() {
        return this.point;
    }

    @NotNull
    public void setPoint(@NotNull Point point) {
        this.point = point;
    }

    @Override
    public double getX() {
        return point.getX();
    }

    @Override
    public double getY() {
        return point.getY();
    }
}

