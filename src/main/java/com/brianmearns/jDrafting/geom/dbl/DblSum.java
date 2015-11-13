package com.brianmearns.jDrafting.geom.dbl;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class DblSum extends AbstractDbl {

    @NotNull
    public final Dbl[] addends;

    public DblSum(@NotNull Dbl... addends) {
        for(Dbl addend : addends) {
            if (addend == null) {
                throw new NullPointerException("Cannot add null Dbls.");
            }
        }
        this.addends = Arrays.copyOf(addends, addends.length);
    }

    @NotNull
    public static Dbl create(@NotNull Dbl... addends) {
        switch(addends.length) {
            case 0:
                return Dbl.zero();
            case 1:
                return addends[0];
            default:
                return new DblSum(addends);
        }
    }

    public double getValue() {
        double sum = 0;
        //Not null enforced in constructor.
        for(Dbl addend : addends) {
            sum += addend.getValue();
        }
        return sum;
    }

}

