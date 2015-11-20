package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 *
 */
public class ScalarMean extends AbstractScalar {

    @NotNull
    public final Scalar[] samples;

    public ScalarMean(@NotNull Scalar... samples) throws IllegalArgumentException {
        if(samples.length < 1) {
            throw new IllegalArgumentException("Cannot compute the mean of no values.");
        }
        for(Scalar sample : samples) {
            if (sample == null) {
                throw new NullPointerException("Cannot take the mean of null Dbls.");
            }
        }
        this.samples = Arrays.copyOf(samples, samples.length);
    }

    @NotNull
    public static Scalar create(@NotNull Scalar... samples) {
        switch(samples.length) {
            case 1:
                return samples[0];
            default:
                return new ScalarMean(samples);
        }
    }

    public double getValue() {
        double sum = 0;
        //Not null enforced in constructor.
        for(Scalar sample : samples) {
            sum += sample.getValue();
        }
        return sum / samples.length;
    }
}
