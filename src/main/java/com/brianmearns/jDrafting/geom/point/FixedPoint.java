package com.brianmearns.jDrafting.geom.point;

import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

/**
 * A FixedPoint is one which has an explicit and immutable X and Y coordinate value, it does not change over time.
 */
public class FixedPoint extends AbstractPoint {
    
    private final double x;
    private final double y;

    public FixedPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Nullable
    private static Point origin;

    @NotNull
    public static Point origin() {
        if(origin == null) {
            origin = new AbstractPoint() {
                @Override
                public double getX() {
                    return 0;
                }
                @Override
                public double getY() {
                    return 0;
                }
            };
        }
        return origin;
    }

    @NotNull
    public static Point create(double x, double y) {
        if(x == 0 && y == 0) {
            return origin();
        }
        return new FixedPoint(x, y);
    }

    /**
     * Returns a Point whose coordinates are fixed at the current value of the given Point's coordinates.
     */
    @NotNull
    public static Point fixPoint(@NotNull Point point) {
        return FixedPoint.create(point.getX(), point.getY());
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

}

