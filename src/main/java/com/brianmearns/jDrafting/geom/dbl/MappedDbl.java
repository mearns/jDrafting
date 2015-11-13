package com.brianmearns.jDrafting.geom.dbl;

import com.google.common.base.Function;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class MappedDbl extends ConvertedDbl {

    @NotNull
    private Function<Double, Double> map;

    protected MappedDbl(@NotNull Function<Double, Double> map, @NotNull Dbl preimage) {
        super(preimage);
        this.map = map;
    }

    @NotNull
    @Contract(pure=true)
    public static MappedDbl create(@NotNull Function<Double, Double> map, @NotNull Dbl preimage) {
        return new MappedDbl(map, preimage);
    }

    /**
     * @throws NullPointerException If the {@link #map} function returns {@code null}.
     */
    @Override
    public double convert(double preimage) throws NullPointerException {
        Double res = map.apply(preimage);
        if(res == null) {
            throw new NullPointerException("Map function returned a null value.");
        }
        return res;
    }
}
