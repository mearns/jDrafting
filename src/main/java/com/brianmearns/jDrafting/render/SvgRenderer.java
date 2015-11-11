package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Reflexive;
import com.brianmearns.jDrafting.Style;
import com.jamesmurty.utils.XMLBuilder2;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

public class SvgRenderer implements Renderer {

    @NotNull
    private static final String svgNamespaceUri = "http://www.w3.org/2000/svg";

    @NotNull
    private XMLBuilder2 builder;

    /**
     * Create a new renderer which will render SVG to the given {@link XMLBuilder2} instance.
     * This <em>will not</em> add the SVG root element to the builder, so you will be responsible
     * for doing this yourself. But, in theory, this allows the same builder to be passed to
     * multiple {@link SvgRenderer} instances in turn.
     */
    public SvgRenderer(@NotNull XMLBuilder2 builder) {
        this.builder = builder;
    }

    /**
     * Create a new renderer with a new {@link XMLBuilder2} instance created, using the
     * fully qualified SVG root element.
     */
    public SvgRenderer() {
        this(XMLBuilder2.create("svg", svgNamespaceUri).namespace("svg", svgNamespaceUri));
    }


    @NotNull
    public PathBuilder path(@Nullable Style style) {
        return new SvgPathBuilder(style);
    }

    @NotNull
    @Reflexive
    public SvgRenderer ellipse(@Nullable Style style, double center_x, double center_y, double radius_x, double radius_y) {
        builder.element("ellipse", svgNamespaceUri)
            .a("cx", String.valueOf(center_x))
            .a("cy", String.valueOf(center_y))
            .a("rx", String.valueOf(radius_x))
            .a("ry", String.valueOf(radius_y))
            .up();
        return this;
    }

    @NotNull
    @Reflexive
    public SvgRenderer circle(@Nullable Style style, double center_x, double center_y, double radius) {
        builder.element("circle", svgNamespaceUri)
            .a("cx", String.valueOf(center_x))
            .a("cy", String.valueOf(center_y))
            .a("r", String.valueOf(radius))
            .up();
        return this;
    }

    @NotNull
    @Reflexive
    public SvgRenderer line(@Nullable Style style, double start_x, double start_y, double end_x, double end_y) {
        builder.element("line", svgNamespaceUri)
            .a("x1", String.valueOf(sx))
            .a("y1", String.valueOf(sy))
            .a("x2", String.valueOf(ex))
            .a("y2", String.valueOf(ey))
            .up();
        return this;
    }

    @NotNull
    public SvgPolygonBuilder polygon(@Nullable Style style) {
        return new SvgPolygonBuilder(style);
    }


    @NotNull
    public SvgPolylineBuilder polyline(@Nullable Style style) {
        return new SvgPolylineBuilder(style);
    }

    @NotNull
    @Reflexive
    public Renderer rect(@Nullable Style style, double x, double y, double width, double height) {
        builder.element("line", svgNamespaceUri)
            .a("x", String.valueOf(x))
            .a("y", String.valueOf(y))
            .a("width", String.valueOf(width))
            .a("height", String.valueOf(height))
            .up();
        return this;
    }

    @NotNull
    @Reflexive
    public Renderer rect(@Nullable Style style, double x, double y, double width, double height, double rx, double ry) {
        builder.element("line", svgNamespaceUri)
            .a("x", String.valueOf(x))
            .a("y", String.valueOf(y))
            .a("width", String.valueOf(width))
            .a("height", String.valueOf(height))
            .a("rx", String.valueOf(rx))
            .a("ry", String.valueOf(ry))
            .up();
        return this;
    }

    @NotNull
    @Reflexive
    public Renderer group(@Nullable Style style) {
        return group(style, null);
    }

    @NotNull
    @Reflexive
    public Renderer group(@Nullable Style style, @Nullable Transformation transformation) {
        //TODO: Add transformation
        builder = builder.element("g", svgNamespaceUri);
        return this;
    }

    @NotNull
    @Reflexive
    public Renderer group(@Nullable Transformation transformation) {
        return group(null, transformation);
    }

    @NotNull
    @Reflexive
    public Renderer endGroup() {
        builder = builder.up();
        return this;
    }

    @NotNull
    @Reflexive
    public Renderer text(@Nullable Style style, double x, double y, @NotNull String text) {
        builder.element("text", svgNamespaceUri)
            .a("x", String.valueOf(x))
            .a("y", String.valueOf(y))
            .t(text)
            .up();
        return this;
    }


    public class PointBuilder<B extends SvgPolyBuilder> {
        @NotNull
        private final B builder;

        PointBuilder(@NotNull B builder) {
            this.builder = builder;
        }

        @NotNull
        public PointBuilderX x(double x) {
            return new PointBuilderX<>(builder, x);
        }

        @NotNull
        public PointBuilderY y(double y) {
            return new PointBuilderY<>(builder, y);
        }
    }


    public class PointBuilderX<B extends SvgPolyBuilder> {

        @NotNull
        private final B builder;
        private final double x;

        PointBuilderX(@NotNull B builder, double x) {
            this.builder = builder;
            this.x = x;
        }

        public B y(double y) {
            return this.builder.point(x, y);
        }
    }

    public class PointBuilderY<B extends SvgPolyBuilder> {

        @NotNull
        private final B builder;
        private final double y;

        PointBuilderY(@NotNull B builder, double y) {
            this.builder = builder;
            this.y = y;
        }

        public B x(double x) {
            return this.builder.point(x, y);
        }
    }

    protected abstract class SvgPolyBuilder<R extends SvgPolyBuilder<R>> implements PolyBuilder<SvgRenderer> {

        @NotNull
        private final List<Point> points = new LinkedList<>();

        @Nullable
        private final Style style;

        SvgPolyBuilder(@Nullable Style style) {
            this.style = style;
        }

        @NotNull
        @Reflexive
        public SvgPolyBuilder point(double x, double y) {
            points.add(new Point(x, y));
        }

        public PointBuilder<R> point() {
            return new PointBuilder<>(this);
        }

        public PointBuilderX<R> x(double x) {
            return new PointBuilderX<>(this, x);
        }

        public PointBuilderY<R> y(double y) {
            return new PointBuilderY<>(this, y);
        }

        @NotNull
        public SvgRenderer endPoly() {
            StringBuilder attrBuilder = new StringBuilder();
            for(Point pt : points) {
                attrBuilder.append(pt.getX()).append(',').append(pt.getY()).append(' ');
            }
            builder.element(getTagName(), svgNamespaceUri).a("points", attrBuilder.toString());
            return SvgPolyBuilder.this;
        }

        @NotNull
        protected abstract String getTagName();

        private class Point {
            private final double x;
            private final double y;

            Point(double x, double y) {
                this.x = x;
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public double getY() {
                return y;
            }
        }
    }

    protected class SvgPolylineBuilder extends SvgPolyBuilder<SvgPolylineBuilder> {
        SvgPolylineBuilder(@Nullable Style style) {
            super(style);
        }

        @Override
        @NotNull
        protected String getTagName() {
            return "polyline";
        }

    }

    protected class SvgPolygonBuilder extends SvgPolyBuilder<SvgPolygonBuilder> {
        SvgPolygonBuilder(@Nullable Style style) {
            super(style);
        }

        @Override
        @NotNull
        protected String getTagName() {
            return "polygon";
        }
    }

    protected class SvgPathBuilder implements PathBuilder<SvgRenderer> {
        @Nullable
        private final Style style;

        @NotNull
        private final StringBuilder data;

        SvgPathBuilder(@Nullable Style style) {
            this.style = style;
            data = new StringBuilder();
        }

        @NotNull
        public SvgRenderer endPath() {
            builder.element("path", svgNamespaceUri).a("d", data.toString()).up();
            return SvgRenderer.this;
        }

        @NotNull
        public SvgRenderer closeAndEndPath() {
            closePath();
            return endPath();
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder closePath() {
            data.append(" Z");
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder moveTo(double x, double y) {
            data.append(" M").append(x).append(',').append(y);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder move(double dx, double dy) {
            data.append(" m").append(dx).append(',').append(dy);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder lineTo(double x, double y) {
            data.append(" L").append(x).append(',').append(y);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder line(double dx, double dy) {
            data.append(" l").append(dx).append(',').append(dy);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder curveTo(double cx, double cy, double x, double y) {
            data.append(" Q").append(cx).append(',').append(cy).append(' ').append(x).append(',').append(y);
            return this;
        }

        @NotNull
        @Reflexive
        public PathBuilder<R> curve(double dcx, double dcy, double dx, double dy) {
            data.append(" q").append(dcx).append(',').append(dcy).append(' ').append(dx).append(',').append(dy);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder curveTo(double c1x, double c1y, double c2x, double c2y, double x, double y) {
            data.append(" C").append(c1x).append(',').append(c1y).append(' ').append(c2x).append(',').append(c2y).append(' ').append(x).append(',').append(y);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder curve(double dc1x, double dc1y, double dc2x, double dc2y, double dx, double dy) {
            data.append(" c").append(dc1x).append(',').append(dc1y).append(' ').append(dc2x).append(',').append(dc2y).append(' ').append(dx).append(',').append(dy);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder arcTo(double radiusX, double radiusY, double xAxisRotate, boolean largestArc, SweepDirection sweep, double x, double y) {
            data.append(" A").append(radiusX).append(' ').append(radiusY)
                .append(' ').append(xAxisRotate)
                .append(' ').append(largestArc ? 1 : 0)
                .append(' ').append(sweep == Clockwise ? 1 : 0)
                .append(' ').append(x).append(',').append(y);
            return this;
        }

        @NotNull
        @Reflexive
        public SvgPathBuilder arc(double radiusX, double radiusY, double xAxisRotate, boolean largestArc, SweepDirection sweep, double dx, double dy) {
            data.append(" a").append(radiusX).append(' ').append(radiusY)
                .append(' ').append(xAxisRotate)
                .append(' ').append(largestArc ? 1 : 0)
                .append(' ').append(sweep == Clockwise ? 1 : 0)
                .append(' ').append(dx).append(',').append(dy);
            return this;
        }

    }

}

