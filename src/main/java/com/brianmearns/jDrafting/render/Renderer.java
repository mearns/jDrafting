package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Endomorphic;
import com.brianmearns.contracts.Reflexive;
import com.brianmearns.jDrafting.Style;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

public interface Renderer {

    /**
     * Add a path, which needs to be defined with the given {@link PathBuilder}.
     * The {@link PathBuilder} can be updated anytime prior to rendering, even if
     * other rendering methods have been invoked in the mean time.
     */
    @NotNull
    public PathBuilder path(@Nullable Style style);

    @NotNull
    @Reflexive
    public Renderer ellipse(@Nullable Style style, double center_x, double center_y, double radius_x, double radius_y);

    @NotNull
    @Reflexive
    public Renderer circle(@Nullable Style style, double center_x, double center_y, double radius);

    @NotNull
    @Reflexive
    public Renderer line(@Nullable Style style, double start_x, double start_y, double end_x, double end_y);

    @NotNull
    public PolyBuilder polygon(@Nullable Style style);

    @NotNull
    public PolyBuilder polyline(@Nullable Style style);

    @NotNull
    @Reflexive
    public Renderer rect(@Nullable Style style, double x, double y, double width, double height);

    @NotNull
    @Reflexive
    public Renderer rect(@Nullable Style style, double x, double y, double width, double height, double rx, double ry);

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
    public Renderer group(@Nullable Style style);

    @NotNull
    @Endomorphic
    public Renderer group(@Nullable Style style, @Nullable Transformation transformation);

    @NotNull
    @Endomorphic
    public Renderer group(@Nullable Transformation transformation);

    @NotNull
    @Endomorphic
    public Renderer endGroup();

    @NotNull
    @Reflexive
    public Renderer text(@Nullable Style style, double x, double y, @NotNull String text);

}

