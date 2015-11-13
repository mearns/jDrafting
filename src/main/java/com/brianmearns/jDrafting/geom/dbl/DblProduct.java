package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 *
 */
public class DblProduct extends AbstractDbl {

    @NotNull
    public final Dbl[] multiplicands;

    public DblProduct(@NotNull Dbl... multiplicands) {
        for(Dbl addend : multiplicands) {
            if (addend == null) {
                throw new NullPointerException("Cannot multiply null Dbls.");
            }
        }
        this.multiplicands = Arrays.copyOf(multiplicands, multiplicands.length);
    }

    @NotNull
    public static Dbl create(@NotNull Dbl... multiplicands) {
        switch(multiplicands.length) {
            case 0:
                return Dbl.one();
            case 1:
                return multiplicands[0];
            default:
                return new DblProduct(multiplicands);
        }
    }

    public double getValue() {
        double product = 1;
        //Not null enforced in constructor.
        for(Dbl addend : multiplicands) {
            product *= addend.getValue();
        }
        return product;
    }
}
