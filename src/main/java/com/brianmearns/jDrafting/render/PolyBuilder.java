package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Reflexive;

public interface PolyBuilder<R extends Renderer> {

    @NotNull
    @Reflexive
    public PolyBuilder<R> point(double x, double y);

    @NotNull
    public R endPoly();
    
}

