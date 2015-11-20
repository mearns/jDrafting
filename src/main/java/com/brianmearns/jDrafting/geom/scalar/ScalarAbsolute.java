package com.brianmearns.jDrafting.geom.scalar;

import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class ScalarAbsolute extends ConvertedScalar {

    public ScalarAbsolute(@NotNull Scalar preimage) {
        super(preimage);
    }

    @NotNull
    public static Scalar create(@NotNull Scalar preimage) {
        if(preimage instanceof FixedScalar && preimage.getValue() >= 0) {
            return preimage;
        }
        return new ScalarAbsolute(preimage);
    }

    @Override
    @Contract(pure=true)
    protected double convert(double preimage) {
        return Math.abs(preimage);
    }
}
