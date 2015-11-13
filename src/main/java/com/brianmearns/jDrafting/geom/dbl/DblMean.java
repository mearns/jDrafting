package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 *
 */
public class DblMean extends AbstractDbl {

    @NotNull
    public final Dbl[] samples;

    public DblMean(@NotNull Dbl... samples) throws IllegalArgumentException {
        if(samples.length < 1) {
            throw new IllegalArgumentException("Cannot compute the mean of no values.");
        }
        for(Dbl sample : samples) {
            if (sample == null) {
                throw new NullPointerException("Cannot take the mean of null Dbls.");
            }
        }
        this.samples = Arrays.copyOf(samples, samples.length);
    }

    @NotNull
    public static Dbl create(@NotNull Dbl... samples) {
        switch(samples.length) {
            case 1:
                return samples[0];
            default:
                return new DblMean(samples);
        }
    }

    public double getValue() {
        double sum = 0;
        //Not null enforced in constructor.
        for(Dbl sample : samples) {
            sum += sample.getValue();
        }
        return sum / samples.length;
    }
}
