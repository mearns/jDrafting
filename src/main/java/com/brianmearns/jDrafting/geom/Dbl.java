package com.brianmearns.jDrafting.geom;

import javax.validation.constraints.NotNull;

public interface Dbl {

    double getValue();

    @NotNull
    Dbl sum(@NotNull Dbl addends...);

    @NotNull
    Dbl difference(@NotNull Dbl a, @NotNull Dbl b);

    @NotNull
    Dbl product(@NotNull Dbl multiplicands...);

    @NotNull
    Dbl quotient(@NotNull Dbl dividend, @NotNull Dbl divisor);

    /**
     * Returns a Dbl instance whose value is a function of the value of another
     * instance.
     */
    @NotNull
    Dbl map(@NotNull Function<Double, Double> mapper, @NotNull Dbl preimage);

}

