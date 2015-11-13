package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class DblQuotient extends AbstractDbl {

    @NotNull
    private final Dbl dividend;

    @NotNull
    private final Dbl divisor;

    protected DblQuotient(@NotNull Dbl dividend, @NotNull Dbl divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @NotNull
    public static DblQuotient create(@NotNull Dbl dividend, @NotNull Dbl divisor) {
        return new DblQuotient(dividend, divisor);
    }

    @Override
    public double getValue() {
        return dividend.getValue() / divisor.getValue();
    }
}
