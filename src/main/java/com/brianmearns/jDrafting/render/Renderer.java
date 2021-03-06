package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Endomorphic;
import com.brianmearns.contracts.Reflexive;
import com.brianmearns.jDrafting.art.Style;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

public interface Renderer {

    /**
     * Add a path, which needs to be defined with the given {@link PathBuilder}.
     * The path must be defined with the builder before any other renderer methods
     * are invoked, or the results are undefined.
     */
    @NotNull
    PathBuilder path(@Nullable Style style);

    @NotNull
    @Reflexive
    Renderer ellipse(@Nullable Style style, double center_x, double center_y, double radius_x, double radius_y);

    @NotNull
    @Reflexive
    Renderer circle(@Nullable Style style, double center_x, double center_y, double radius);

    @NotNull
    @Reflexive
    Renderer line(@Nullable Style style, double start_x, double start_y, double end_x, double end_y);

    @NotNull
    PolyBuilder polygon(@Nullable Style style);

    @NotNull
    PolyBuilder polyline(@Nullable Style style);

    @NotNull
    @Reflexive
    Renderer rect(@Nullable Style style, double x, double y, double width, double height);

    @NotNull
    @Reflexive
    Renderer rect(@Nullable Style style, double x, double y, double width, double height, double rx, double ry);

    /**
     * Start a group into which new elements will be added, until the group is closed.
     * The style applied to the group is inherited by default by all child elements.
     *
     * <p>
     * The returned object is <em>not</em> necessarily this object, it could be implemented as
     * a child object. Regardless, use {@link #endGroup()} to exit the group.
     */
    @NotNull
    @Endomorphic
    Renderer group(@Nullable Style style);

    @NotNull
    @Endomorphic
    Renderer group(@Nullable Style style, @Nullable Transformation transformation);

    @NotNull
    @Endomorphic
    Renderer group(@Nullable Transformation transformation);

    @NotNull
    @Endomorphic
    Renderer endGroup();

    @NotNull
    @Reflexive
    Renderer text(@Nullable Style style, double x, double y, @NotNull String text);

}

