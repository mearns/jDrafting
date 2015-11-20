package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.AbstractScalar;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class TwoPointScalar extends AbstractScalar {

    @NotNull
    protected final Point pt1;

    @NotNull
    protected final Point pt2;

    public TwoPointScalar(@NotNull Point pt1, @NotNull Point pt2) {
        this.pt1 = pt1;
        this.pt2 = pt2;
    }

    protected Point getPt1() {
        return pt1;
    }

    protected Point getPt2() {
        return pt2;
    }

    protected <T> T apply(TwoPointFunction<T> func) {
        return func.apply(pt1, pt2);
    }

    protected <T> T apply(TwoPointCoordinateFunction<T> func) {
        return func.apply(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
    }

    public interface TwoPointCoordinateFunction<T> {
        T apply(double x1, double y1, double x2, double y2);
    }

    public interface TwoPointFunction<T> {
        T apply(@NotNull Point pt1, @NotNull Point pt2);
    }
}
