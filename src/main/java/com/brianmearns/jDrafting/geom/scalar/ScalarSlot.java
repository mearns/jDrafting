package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;

/**
 * A {@link Scalar} which is just a wrapper around another, settable, {@link Scalar}
 * object.
 */
public class ScalarSlot extends AbstractScalar {

    @NotNull
    private Scalar scalar;

    protected ScalarSlot(@NotNull Scalar scalar) {
        this.scalar = scalar;
    }

    /**
     * Factory method.
     */
    @NotNull
    public static ScalarSlot create(@NotNull Scalar scalar) {
        return new ScalarSlot(scalar);
    }

    @NotNull
    public Scalar getScalar() {
        return this.scalar;
    }

    @NotNull
    public void setScalar(@NotNull Scalar scalar) {
        this.scalar = scalar;
    }

    /**
     * This is the core function of this interface, it returns the <em>current</em> numeric value of the object.
     */
    @Override
    public double getValue() {
        return scalar.getValue();
    }
}
