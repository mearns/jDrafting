package com.brianmearns.jDrafting.geom.scalar;

import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class ScalarNegative extends ConvertedScalar {

    public ScalarNegative(@NotNull Scalar preimage) {
        super(preimage);
    }

    @NotNull
    public static Scalar create(@NotNull Scalar preimage) {
        if(preimage instanceof FixedScalar && preimage.getValue() == 0) {
            return preimage;
        }
        return new ScalarNegative(preimage);
    }

    @Override
    @Contract(pure=true)
    protected double convert(double preimage) {
        return -(preimage);
    }
}
