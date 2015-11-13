package com.brianmearns.jDrafting.geom.dbl;

import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class DblAbsolute extends ConvertedDbl {

    public DblAbsolute(@NotNull Dbl preimage) {
        super(preimage);
    }

    @NotNull
    public static Dbl create(@NotNull Dbl preimage) {
        if(preimage instanceof FixedDbl && preimage.getValue() >= 0) {
            return preimage;
        }
        return new DblAbsolute(preimage);
    }

    @Override
    @Contract(pure=true)
    protected double convert(double preimage) {
        return Math.abs(preimage);
    }
}
