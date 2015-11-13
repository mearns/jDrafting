package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class DblDifference extends AbstractDbl {

    @NotNull
    private final Dbl a;

    @NotNull
    private final Dbl b;

    public DblDifference(@NotNull Dbl a, @NotNull Dbl b) {
        this.a = a;
        this.b = b;
    }

    @NotNull
    public static Dbl create(@NotNull Dbl a, @NotNull Dbl b) {
        if(b instanceof FixedDbl && b.getValue() == 0) {
            return a;
        }
        return new DblDifference(a, b);
    }

    @Override
    public double getValue() {
        return a.getValue() - b.getValue();
    }
}
