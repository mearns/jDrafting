package com.brianmearns.jDrafting.render;

import com.brianmearns.contracts.Reflexive;
import com.brianmearns.jDrafting.Style;
import com.jamesmurty.utils.XMLBuilder2;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

public class SvgRenderer implements Renderer {

    private static final String svgNamespaceUri = "http://www.w3.org/2000/svg";

    @NotNull
    private final List<Node> nodes = new LinkedList<>();

    @NotNull
    @Reflexive
    protected SvgRenderer add(@NotNull Node node) {
        nodes.add(node);
        return this;
    }

    @NotNull
    @Reflexive
    public SvgRenderer ellipse(@Nullable Style style, double center_x, double center_y, double radius_x, double radius_y) {
        return add(new EllipseNode(style, center_x, center_y, radius_x, radius_y));
    }

    protected static class EllipseNode extends AbstractNode {
        private final double center_x;
        private final double center_y;
        private final double radius_x;
        private final double radius_y;

        EllipseNode(@Nullable Style style, double center_x, double center_y, double radius_x, double radius_y) {
            super(style);
            this.center_x = center_x;
            this.center_y = center_y;
            this.radius_x = radius_x;
            this.radius_y = radius_y;
        }

        @Override
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder) {
            return super.build(builder.e("ellipse", svgNamespaceUri)
                .a("cx", String.valueOf(center_x))
                .a("cy", String.valueOf(center_y))
                .a("rx", String.valueOf(radius_x))
                .a("ry", String.valueOf(radius_y)))
                .up();
        }
    }

    @NotNull
    @Reflexive
    public SvgRenderer circle(@Nullable Style style, double center_x, double center_y, double radius) {
        return add(new CircleNode(style, center_x, center_y, radius));
    }

    protected static class CircleNode extends AbstractNode {
        private final double center_x;
        private final double center_y;
        private final double radius;

        CircleNode(@Nullable Style style, double center_x, double center_y, double radius) {
            super(style);
            this.center_x = center_x;
            this.center_y = center_y;
            this.radius = radius;
        }

        @Override
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder) {
            return super.build(builder.e("circle", svgNamespaceUri)
                .a("cx", String.valueOf(center_x))
                .a("cy", String.valueOf(center_y))
                .a("r", String.valueOf(radius)))
                .up();
        }
    }

    @NotNull
    @Reflexive
    public SvgRenderer line(@Nullable Style style, double start_x, double start_y, double end_x, double end_y) {
        return add(new LineNode(style, start_x, start_y, end_x, end_y));
    }

    protected static class LineNode extends AbstractNode {
        private final double sx;
        private final double sy;
        private final double ex;
        private final double ey;

        LineNode(@Nullable Style style, double sx, double sy, double ex, double ey) {
            super(style);
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }

        @Override
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder) {
            return super.build(builder.e("line", svgNamespaceUri)
                .a("x1", String.valueOf(sx))
                .a("y1", String.valueOf(sy))
                .a("x2", String.valueOf(ex))
                .a("y2", String.valueOf(ey)))
                .up();
        }
    }

    @NotNull
    public PolyBuilder polygon(@Nullable Style style) {
        return new SvgPolygonBuilder(style);
    }


    protected static abstract class PolyNode extends AbstractNode {
        @NotNull
        private final Point[] points;

        PolyNode(@Nullable Style style, @NotNull List<Point> points) {
            super(style);
            this.points = points.toArray(new Point[points.size()]);
        }

        @NotNull
        protected abstract String getTagName();

        @Override
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder) {
            StringBuilder attrBuilder = new StringBuilder();
            for(Point pt : points) {
                attrBuilder.append(pt.getX()).append(',').append(pt.getY()).append(' ');
            }
            return super.build(builder.e(getTagName(), svgNamespaceUri).a("points", attrBuilder.toString()));
        }
    }

    protected static class PolygonNode extends PolyNode {
        PolygonNode(@Nullable Style style, @NotNull List<Point> points) {
            super(style, points);
        }

        @NotNull
        @Override
        protected String getTagName() {
            return "polygon";
        }
    }

    protected static class PolylineNode extends PolyNode {
        PolylineNode(@Nullable Style style, @NotNull List<Point> points) {
            super(style, points);
        }

        @NotNull
        @Override
        protected String getTagName() {
            return "polyline";
        }
    }


    @NotNull
    public PolyBuilder polyline(@Nullable Style style) {
        return new SvgPolylineBuilder(style);
    }

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
    public Renderer group(@Nullable Style style);

    @NotNull
    public Renderer group(@Nullable Style style, @Nullable Transformation transformation);

    @NotNull
    public Renderer group(@Nullable Transformation transformation);

    @NotNull
    public Renderer endGroup();

    @NotNull
    @Reflexive
    public Renderer text(@Nullable Style, double x, double y, @NotNull String text);



    protected static interface Node {
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder);
    }

    protected static abstract class AbstractNode {
        @Nullable
        private final Style style;

        public AbstractNode(@Nullable Style style) {
            this.style = style;
        }

        @Override
        @NotNull
        public XMLBuilder2 build(@NotNull XMLBuilder2 builder) {
            //Assume the element has already been added to the builder in the child class,
            // we just need to add the attributes for the style to it. Don't go up,
            // the child class may need to add sub elements.
            return builder;
        }

        @Nullable
        public Style getStyle() {
            return style;
        }
    }

    protected static class Point {
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

    protected abstract class SvgPolyBuilder implements PolyBuilder<SvgRenderer> {

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

        @NotNull
        public SvgRenderer endPoly() {
            return SvgPolyBuilder.this.add(buildNode(points));
        }

        @NotNull
        protected abstract Node buildNode(@NotNull List<Point> points);
    }

    protected abstract class SvgPolylineBuilder extends SvgPolyBuilder {
        @NotNull
        protected abstract Node buildNode(@NotNull List<Point> points) {
            return new PolylineNode(points);
        }
    }

    protected abstract class SvgPolygonBuilder extends SvgPolyBuilder {
        @NotNull
        protected abstract Node buildNode(@NotNull List<Point> points) {
            return new PolygonNode(points);
        }
    }

}

