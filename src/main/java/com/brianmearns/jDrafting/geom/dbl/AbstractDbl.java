package com.brianmearns.jDrafting.geom.dbl;

import com.google.common.base.Function;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class AbstractDbl implements Dbl {

    //FIXME: Most of these should be static, and somewhere else.
    // But we should also have instance methods that perform operations between this instance and others.

    @Override
    @NotNull
    public Dbl sum(@NotNull Dbl... addends) {
        return DblSum.create(addends);
    }

    @Override
    @NotNull
    public Dbl mean(@NotNull Dbl... samples) {
        return DblMean.create(samples);
    }

    @Override
    @NotNull
    public Dbl difference(@NotNull Dbl a, @NotNull Dbl b) {
        return DblDifference.create(a, b);
    }

    @Override
    @NotNull
    public Dbl product(@NotNull Dbl... multiplicands) {
        return DblMean.create(multiplicands);
    }

    @Override
    @NotNull
    public Dbl quotient(@NotNull Dbl dividend, @NotNull Dbl divisor) {
        return DblQuotient.create(dividend, divisor);
    }

    @Override
    @NotNull
    public Dbl abs() {
        return DblAbsolute.create(this);
    }

    @Override
    @NotNull
    public Dbl negative() {
        return DblNegative.create(this);
    }

    //XXX: Left off here.

    @Override
    @NotNull
    public Dbl sine() {
        return null;
    }

    @Override
    @NotNull
    public Dbl cosine() {
        return null;
    }

    @Override
    @NotNull
    public Dbl tangent() {
        return null;
    }

    @Override
    @NotNull
    public Dbl log10() {
        return null;
    }

    @Override
    @NotNull
    public Dbl log2() {
        return null;
    }

    /**
     * Returns a Dbl instance whose value is a function of the value of another
     * instance.
     */
    @Override
    @NotNull
    public Dbl map(@NotNull Function<Double, Double> mapper, @NotNull Dbl preimage) {
        return MappedDbl.create(mapper, preimage);
    }
}
