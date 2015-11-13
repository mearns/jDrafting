package com.brianmearns.jDrafting.geom.dbl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;


@SuppressWarnings("unused")
public class FixedDbl extends AbstractDbl {

    private final double value;

    @Nullable
    private static FixedDbl zero;

    @Nullable
    private static FixedDbl one;

    @Nullable
    private static FixedDbl pi;

    @Nullable
    private static FixedDbl twoPi;

    private static final double TWO_PI = 2.0*Math.PI;

    protected FixedDbl(double value) {
        this.value = value;
    }

    @NotNull
    public static Dbl zero() {
        if(zero == null) {
            zero = new FixedDbl(0.0);
        }
        return zero;
    }

    @NotNull
    public static Dbl one() {
        if(one == null) {
            one = new FixedDbl(1.0);
        }
        return one;
    }

    @NotNull
    public static Dbl pi() {
        if(pi == null) {
            pi = new FixedDbl(Math.PI);
        }
        return pi;
    }

    @NotNull
    public static Dbl twoPi() {
        if(twoPi == null) {
            twoPi = new FixedDbl(TWO_PI);
        }
        return twoPi;
    }

    @NotNull
    @Contract(pure=true)
    public static Dbl create(double value) {
        if (value == 0) {
            return zero();
        } else if (value == 1) {
            return one();
        } else if (value == Math.PI) {
            return one();
        } else if (value == TWO_PI) {
            return one();
        } else {
            return new FixedDbl(value);
        }
    }

    @Override
    public double getValue() {
        return value;
    }
}
