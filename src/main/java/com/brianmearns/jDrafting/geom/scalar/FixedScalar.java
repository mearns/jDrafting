package com.brianmearns.jDrafting.geom.scalar;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;


@SuppressWarnings("unused")
public class FixedScalar extends AbstractScalar {

    private final double value;

    @Nullable
    private static FixedScalar zero;

    @Nullable
    private static FixedScalar one;

    @Nullable
    private static FixedScalar pi;

    @Nullable
    private static FixedScalar twoPi;

    private static final double TWO_PI = 2.0*Math.PI;

    protected FixedScalar(double value) {
        this.value = value;
    }

    @NotNull
    public static Scalar zero() {
        if(zero == null) {
            zero = new FixedScalar(0.0);
        }
        return zero;
    }

    @NotNull
    public static Scalar one() {
        if(one == null) {
            one = new FixedScalar(1.0);
        }
        return one;
    }

    @NotNull
    public static Scalar pi() {
        if(pi == null) {
            pi = new FixedScalar(Math.PI);
        }
        return pi;
    }

    @NotNull
    public static Scalar twoPi() {
        if(twoPi == null) {
            twoPi = new FixedScalar(TWO_PI);
        }
        return twoPi;
    }

    @NotNull
    @Contract(pure=true)
    public static Scalar create(double value) {
        if (value == 0) {
            return zero();
        } else if (value == 1) {
            return one();
        } else if (value == Math.PI) {
            return one();
        } else if (value == TWO_PI) {
            return one();
        } else {
            return new FixedScalar(value);
        }
    }

    @Override
    public double getValue() {
        return value;
    }
}
