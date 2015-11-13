package com.brianmearns.jDrafting.geom;

import javax.validation.constraints.NotNull;

public class FixedPoint extends AbstractPoint {
    
    private final double x;
    private final double y;

    protected FixedPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private static final FixedPoint origin;

    @NotNull
    public static FixedPoint origin() {
        if(origin == null) {
            origin = new FixedPoint(0, 0);
        }
        return origin;
    }

    @NotNull
    public static FixedPoint create(double x, double y) {
        if(x == 0 && y == 0) {
            return origin();
        }
        return new FixedPoint(x, y);
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

