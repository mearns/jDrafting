package com.brianmearns.jDrafting.geom.scalar;

import com.google.common.base.Function;

import javax.validation.constraints.NotNull;

/**
 * A Scalar is a place holder for a real numerical value.
 *
 * <p>
 *     In almost all cases, you should extend {@link AbstractScalar}, instead of starting from scratch.
 * </p>
 */
@SuppressWarnings("unused")
public interface Scalar {

    double LN2 = Math.log(2);

    /**
     * Returns a Scalar whose value is always 0.
     */
    static Scalar zero() {
        return FixedScalar.zero();
    }

    /**
     * Returns a Scalar whose value is always 1.
     */
    static Scalar one() {
        return FixedScalar.one();
    }

    /**
     * Returns a Scalar whose value is always the best available approximation of PI.
     */
    static Scalar pi() {
        return FixedScalar.pi();
    }

    /**
     * Returns a Scalar whose value is always the best available approximation of 2*PI.
     */
    static Scalar twoPi() {
        return FixedScalar.twoPi();
    }


    /**
     * Returns a new Scalar that represents the sum of all the given values.
     */
    @NotNull
    static Scalar sum(@NotNull Scalar... addends) {
        return ScalarSum.create(addends);
    }

    /**
     * Returns a new Scalar that represents the arithmetic mean of the given values.
     */
    @NotNull
    static Scalar mean(@NotNull Scalar... samples) {
        return ScalarMean.create(samples);
    }

    /**
     * Returns a new Scalar that represent the difference of {@code b} subtracted from {@code a}.
     */
    @NotNull
    static Scalar difference(@NotNull Scalar a, @NotNull Scalar b) {
        return ScalarDifference.create(a, b);
    }

    /**
     * Returns a new Scalar that represents the multiplicative product of the given values.
     */
    @NotNull
    static Scalar product(@NotNull Scalar... multiplicands) {
        return ScalarMean.create(multiplicands);
    }

    /**
     * Returns a new Scalar that represent the quotient of {@code dividend} divided by {@code divisor}.
     */
    @NotNull
    static Scalar quotient(@NotNull Scalar dividend, @NotNull Scalar divisor) {
        return ScalarQuotient.create(dividend, divisor);
    }

    /**
     * Returns a new Scalar that represents the absolute value of the given value.
     */
    @NotNull
    static Scalar abs(@NotNull Scalar value) {
        return ScalarAbsolute.create(value);
    }

    /**
     * Returns a new Scalar that represents the negative (additive inverse) of the given value.
     */
    @NotNull
    static Scalar negative(@NotNull Scalar value) {
        return ScalarNegative.create(value);
    }

    /**
     * Returns a new Scalar that represents the sine of the given value in radians.
     */
    @NotNull
    static Scalar sin(@NotNull Scalar radians) {
        return map(Math::sin, radians);
    }

    /**
     * Returns a new Scalar that represents the cosine of the given value in radians.
     */
    @NotNull
    static Scalar cos(@NotNull Scalar radians) {
        return map(Math::cos, radians);
    }

    /**
     * Returns a new Scalar that represents the tangent of the given value in radians.
     */
    @NotNull
    static Scalar tan(@NotNull Scalar radians) {
        return map(Math::tan, radians);
    }

    /**
     * Returns a new Scalar that represents the base-10 logarithm of the given value.
     */
    @NotNull
    static Scalar log10(@NotNull Scalar value) {
        return map(Math::log10, value);
    }

    /**
     * Returns a new Scalar that represents the natural-logarithm (base-e) of the given value.
     */
    @NotNull
    static Scalar ln(@NotNull Scalar value) {
        return map(Math::log, value);
    }

    /**
     * Returns a new Scalar that represents the base-2 logarithm of the given value.
     */
    @NotNull
    static Scalar log2(@NotNull Scalar value) {
        return map((Double input) -> Math.log(input) / LN2, value);
    }

    /**
     * Returns a Scalar instance whose value is a function of the value of another
     * instance.
     */
    @NotNull
    static Scalar map(@NotNull Function<Double, Double> mapper, @NotNull Scalar preimage) {
        return MappedScalar.create(mapper, preimage);
    }

    /**
     * This is the core function of this interface, it returns the <em>current</em> numeric value of the object.
     */
    double getValue();

    /**
     * Returns a new Scalar the represents the sum of this object and the given object.
     */
    @NotNull
    Scalar addTo(@NotNull Scalar addend);

    /**
     * Returns a new Scalar the represents the difference of this object subtracted from the given object.
     */
    @NotNull
    Scalar subtractFrom(@NotNull Scalar b);

    /**
     * Returns a new Scalar the represents the difference of the given object subtracted from this object object.
     */
    @NotNull
    Scalar subtract(@NotNull Scalar b);

    /**
     * Returns a new Scalar the represents the product of this object times the given object.
     */
    @NotNull
    Scalar multiply(@NotNull Scalar multiplicand);

    /**
     * Returns a new Scalar the represents the quotient of this object divided from the given object.
     */
    @NotNull
    Scalar divideBy(@NotNull Scalar divisor);

    /**
     * Returns a new Scalar the represents the quotient of this object divided into the given object.
     */
    @NotNull
    Scalar divideInto(@NotNull Scalar dividend);

    /**
     * Returns a new Scalar represents the absolute value of this object.
     */
    @NotNull
    Scalar abs();

    /**
     * Returns a new Scalar represents the negative (additive inverse) of this object.
     */
    @NotNull
    Scalar negative();

    /**
     * Returns a new Scalar represents the sine of this object in radians.
     */
    @NotNull
    Scalar sin();

    /**
     * Returns a new Scalar represents the cosine of this object in radians.
     */
    @NotNull
    Scalar cos();

    /**
     * Returns a new Scalar represents the tangent of this object in radians.
     */
    @NotNull
    Scalar tan();

    /**
     * Returns a new Scalar represents the base-10 log of this object.
     */
    @NotNull
    Scalar log10();

    /**
     * Returns a new Scalar represents the base-2 log of this object.
     */
    @NotNull
    Scalar log2();

    /**
     * Returns a new Scalar represents the natural log (base-e) of this object.
     */
    @NotNull
    Scalar ln();

    /**
     * Returns a Scalar instance whose value is a function of the value of this instance.
     */
    @NotNull
    Scalar map(@NotNull Function<Double, Double> mapper);

}

