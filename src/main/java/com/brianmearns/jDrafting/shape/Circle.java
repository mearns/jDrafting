package com.brianmearns.jDrafting.shape;


import com.brianmearns.jDrafting.art.DefaultStylable;
import com.brianmearns.jDrafting.geom.point.Point;
import com.brianmearns.jDrafting.geom.scalar.Scalar;
import com.brianmearns.jDrafting.render.Renderer;
import org.jetbrains.annotations.NotNull;


public abstract class Circle extends DefaultStylable implements Shape<Circle> {

    @NotNull
    public abstract Point getCenter();

    @NotNull
    public abstract Scalar getRadius();

    @Override
    public void render(@NotNull Renderer renderer) {
        final Point center = getCenter();
        renderer.circle(getStyle(), center.getX(), center.getY(), getRadius().getValue());
    }

    protected static abstract class ScaledCircle extends Circle {

        @NotNull
        private final ShapeScaler scaler;

        @NotNull
        private final Circle original;


        public ScaledCircle(@NotNull Scalar scale, @NotNull Circle original) {
            scaler = ShapeScaler.create(scale);
            this.original = original;
        }

        @NotNull
        @Override
        public Point getCenter() {
            return scaler.transformPoint(original.getCenter());
        }

        //XXX: Left off here. Haven't decided how to handle scalars yet. Um. Uh.
    }
}

