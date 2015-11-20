package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class ScalarSum extends AbstractScalar {

    @NotNull
    public final Scalar[] addends;

    public ScalarSum(@NotNull Scalar... addends) {
        for(Scalar addend : addends) {
            if (addend == null) {
                throw new NullPointerException("Cannot add null Dbls.");
            }
        }
        this.addends = Arrays.copyOf(addends, addends.length);
    }

    @NotNull
    public static Scalar create(@NotNull Scalar... addends) {
        switch(addends.length) {
            case 0:
                return Scalar.zero();
            case 1:
                return addends[0];
            default:
                return new ScalarSum(addends);
        }
    }

    public double getValue() {
        double sum = 0;
        //Not null enforced in constructor.
        for(Scalar addend : addends) {
            sum += addend.getValue();
        }
        return sum;
    }

}

