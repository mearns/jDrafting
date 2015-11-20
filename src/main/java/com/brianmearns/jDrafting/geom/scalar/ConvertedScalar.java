package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class ConvertedScalar extends AbstractScalar {

    @NotNull
    private Scalar preimage;

    public ConvertedScalar(Scalar preimage) {
        this.preimage = preimage;
    }

    protected abstract double convert(double preimage);

    @NotNull
    public Scalar getPreimage() {
        return preimage;
    }

    @Override
    public double getValue() {
        return convert(getPreimage().getValue());
    }
}
