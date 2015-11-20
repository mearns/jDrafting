package com.brianmearns.jDrafting.geom.scalar;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 *
 */
public class ScalarProduct extends AbstractScalar {

    @NotNull
    public final Scalar[] multiplicands;

    public ScalarProduct(@NotNull Scalar... multiplicands) {
        for(Scalar addend : multiplicands) {
            if (addend == null) {
                throw new NullPointerException("Cannot multiply null Dbls.");
            }
        }
        this.multiplicands = Arrays.copyOf(multiplicands, multiplicands.length);
    }

    @NotNull
    public static Scalar create(@NotNull Scalar... multiplicands) {
        switch(multiplicands.length) {
            case 0:
                return Scalar.one();
            case 1:
                return multiplicands[0];
            default:
                return new ScalarProduct(multiplicands);
        }
    }

    public double getValue() {
        double product = 1;
        //Not null enforced in constructor.
        for(Scalar addend : multiplicands) {
            product *= addend.getValue();
        }
        return product;
    }
}
