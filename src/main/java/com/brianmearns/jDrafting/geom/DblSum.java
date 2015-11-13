package com.brianmearns.jDrafting.geom;

import javax.validation.constraints.NotNull;

public class DblSum extends AbstractDbl {

    @NotNull
    public final Dbl[] addends;

    protected DblSum(@NotNull Dbl addends...) {
        for(Dbl addend : addends) {
            if (addend == null) {
                throw new NullPointException("Cannot add null Dbls.");
            }
        }
        this.addends = Arrays.copyOf(addends);
    }

    @NotNull
    public static Sum create(@NotNull Dbl addends...) {
        if(addends.length == 0) {
            return Dbl.zero();
        }
        return new DblSum(addends);
    }

    public double getValue() {
        double sum = 0;
        for(Dbl addend : addends) {
            sum += addend.getValue();
        }
        return sum;
    }

}

