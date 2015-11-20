package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class ScalarDifference extends AbstractScalar {

    @NotNull
    private final Scalar a;

    @NotNull
    private final Scalar b;

    public ScalarDifference(@NotNull Scalar a, @NotNull Scalar b) {
        this.a = a;
        this.b = b;
    }

    @NotNull
    public static Scalar create(@NotNull Scalar a, @NotNull Scalar b) {
        if(b instanceof FixedScalar && b.getValue() == 0) {
            return a;
        }
        return new ScalarDifference(a, b);
    }

    @Override
    public double getValue() {
        return a.getValue() - b.getValue();
    }
}
