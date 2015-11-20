package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class ScalarQuotient extends AbstractScalar {

    @NotNull
    private final Scalar dividend;

    @NotNull
    private final Scalar divisor;

    protected ScalarQuotient(@NotNull Scalar dividend, @NotNull Scalar divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @NotNull
    public static ScalarQuotient create(@NotNull Scalar dividend, @NotNull Scalar divisor) {
        return new ScalarQuotient(dividend, divisor);
    }

    @Override
    public double getValue() {
        return dividend.getValue() / divisor.getValue();
    }
}
