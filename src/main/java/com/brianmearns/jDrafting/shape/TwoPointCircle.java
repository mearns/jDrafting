package com.brianmearns.jDrafting.shape;

import com.brianmearns.jDrafting.geom.point.Point;
import com.brianmearns.jDrafting.geom.point.PointSlot;
import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


/**
 * A circle defined by two points: it's center and any point on the perimeter.
 */
public class TwoPointCircle extends Circle {

    @NotNull
    private final PointSlot center;

    @NotNull
    private final PointSlot up;

    @NotNull
    private final Scalar radius;

    public TwoPointCircle(@NotNull PointSlot center, @NotNull PointSlot up) {
        this.center = center;
        this.up = up;
        radius = up.distance(center);
    }

    @NotNull
    @Contract(pure=true)
    public static Circle create(@NotNull PointSlot center, @NotNull PointSlot up) {
        return new TwoPointCircle(center, up);
    }

    @NotNull
    @Override
    public Point getCenter() {
        return center.getView();
    }

    @NotNull
    @Override
    public Scalar getRadius() {
        return radius;
    }

    @Override
    public Circle scale(@NotNull Scalar xfactor, @NotNull Scalar yfactor) {
        return null;
    }

    @Override
    public Circle translate(@NotNull Scalar dx, @NotNull Scalar dy) {
        return null;
    }

    @Override
    public Circle rotate(@NotNull Scalar degrees) {
        return null;
    }
}
