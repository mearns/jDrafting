package com.brianmearns.jDrafting.geom.scalar;

import com.google.common.base.Function;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class MappedScalar extends ConvertedScalar {

    @NotNull
    private Function<Double, Double> map;

    protected MappedScalar(@NotNull Function<Double, Double> map, @NotNull Scalar preimage) {
        super(preimage);
        this.map = map;
    }

    @NotNull
    @Contract(pure=true)
    public static MappedScalar create(@NotNull Function<Double, Double> map, @NotNull Scalar preimage) {
        return new MappedScalar(map, preimage);
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
