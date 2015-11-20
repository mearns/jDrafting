package com.brianmearns.jDrafting.geom.scalar;

import com.google.common.base.Function;

import javax.validation.constraints.NotNull;

/**
 *
 */
public abstract class AbstractScalar implements Scalar {

    @Override
    @NotNull
    public Scalar abs() {
        return Scalar.abs(this);
    }

    @Override
    @NotNull
    public Scalar negative() {
        return Scalar.negative(this);
    }

    @Override
    @NotNull
    public Scalar sin() {
        return Scalar.sin(this);
    }

    @Override
    @NotNull
    public Scalar cos() {
        return Scalar.cos(this);
    }

    @Override
    @NotNull
    public Scalar tan() {
        return Scalar.tan(this);
    }

    @Override
    @NotNull
    public Scalar log10() {
        return Scalar.log10(this);
    }

    @Override
    @NotNull
    public Scalar log2() {
        return Scalar.log2(this);
    }

    @Override
    public Scalar ln() {
        return Scalar.ln(this);
    }

    /**
     * Returns a Scalar instance whose value is a function of the value of another
     * instance.
     */
    @Override
    @NotNull
    public Scalar map(@NotNull Function<Double, Double> mapper) {
        return MappedScalar.create(mapper, this);
    }

    @Override
    public Scalar addTo(@NotNull Scalar addend) {
        return Scalar.sum(this, addend);
    }

    @Override
    public Scalar subtractFrom(@NotNull Scalar b) {
        return Scalar.difference(b, this);
    }

    @Override
    public Scalar subtract(@NotNull Scalar b) {
        return Scalar.difference(this, b);
    }

    @Override
    public Scalar multiply(@NotNull Scalar multiplicand) {
        return Scalar.product(this, multiplicand);
    }

    @Override
    public Scalar divideBy(@NotNull Scalar divisor) {
        return Scalar.quotient(this, divisor);
    }

    @Override
    public Scalar divideInto(@NotNull Scalar dividend) {
        return Scalar.quotient(dividend, this);
    }


}
