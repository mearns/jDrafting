package com.brianmearns.jDrafting.shape;

import com.brianmearns.jDrafting.geom.point.Point;
import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class ShapeScaler {

    @NotNull
    private final Scalar factor;

    public ShapeScaler(@NotNull Scalar factor) {
        this.factor = factor;
    }

    @NotNull
    @Contract(pure=true)
    public static ShapeScaler create(@NotNull Scalar factor) {
        return new ShapeScaler(factor);
    }

    @NotNull
    protected Point transformPoint(@NotNull Point pt) {
        return pt.dilate(factor);
    }

}
