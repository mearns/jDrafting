package com.brianmearns.jDrafting.shape;


import com.brianmearns.jDrafting.art.DefaultStylable;
import com.brianmearns.jDrafting.geom.point.Point;
import com.brianmearns.jDrafting.geom.scalar.Scalar;
import com.brianmearns.jDrafting.render.Renderer;

import javax.validation.constraints.NotNull;

public abstract class Circle extends DefaultStylable implements Shape {

    @NotNull
    public abstract Point getCenter();

    @NotNull
    public abstract Scalar getRadius();

    @Override
    public void render(@NotNull Renderer renderer) {
        final Point center = getCenter();
        renderer.circle(getStyle(), center.getX(), center.getY(), getRadius().getValue());
    }
}

