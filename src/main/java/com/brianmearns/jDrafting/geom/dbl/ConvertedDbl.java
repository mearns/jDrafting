package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class ConvertedDbl extends AbstractDbl {

    @NotNull
    private Dbl preimage;

    public ConvertedDbl(Dbl preimage) {
        this.preimage = preimage;
    }

    protected abstract double convert(double preimage);

    @NotNull
    public Dbl getPreimage() {
        return preimage;
    }

    @Override
    public double getValue() {
        return convert(getPreimage().getValue());
    }
}
