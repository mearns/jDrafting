package com.brianmearns.jDrafting.shape;

import com.brianmearns.jDrafting.art.Renderable;
import com.brianmearns.jDrafting.art.Stylable;
import com.brianmearns.jDrafting.geom.scalar.Scalar;

import javax.validation.constraints.NotNull;

/**
 * A shape is something
 */
public interface Shape<S extends Shape<S>> extends Renderable, Stylable {

    @NotNull
    S scale(@NotNull Scalar xfactor, @NotNull Scalar yfactor);

    @NotNull
    S translate(@NotNull Scalar dx, @NotNull Scalar dy);

    @NotNull
    S rotate(@NotNull Scalar degrees);

}

