package com.brianmearns.jDrafting.geom.dbl;

import com.google.common.base.Function;

import javax.validation.constraints.NotNull;

public interface Dbl {


    static Dbl zero() {
        return FixedDbl.zero();
    }

    static Dbl one() {
        return FixedDbl.one();
    }

    static Dbl pi() {
        return FixedDbl.pi();
    }

    static Dbl twoPi() {
        return FixedDbl.twoPi();
    }

    double getValue();

    @NotNull
    Dbl sum(@NotNull Dbl... addends);

    @NotNull
    Dbl mean(@NotNull Dbl... samples);

    @NotNull
    Dbl difference(@NotNull Dbl a, @NotNull Dbl b);

    @NotNull
    Dbl product(@NotNull Dbl... multiplicands);

    @NotNull
    Dbl quotient(@NotNull Dbl dividend, @NotNull Dbl divisor);

    @NotNull
    Dbl abs();

    @NotNull
    Dbl negative();

    @NotNull
    Dbl sine();

    @NotNull
    Dbl cosine();

    @NotNull
    Dbl tangent();

    @NotNull
    Dbl log10();

    @NotNull
    Dbl log2();

    /**
     * Returns a Dbl instance whose value is a function of the value of another
     * instance.
     */
    @NotNull
    Dbl map(@NotNull Function<Double, Double> mapper, @NotNull Dbl preimage);

}

