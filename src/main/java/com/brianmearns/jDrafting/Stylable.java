package com.brianmearns.jDrafting;

import com.brianmearns.jDrafting.render.PathBuilder;

import javax.validation.constraints.NotNull;

public interface Stylable {

    @NotNull
    public PathBuilder setStyle(@NotNull Style style);

}

