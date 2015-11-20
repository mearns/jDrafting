package com.brianmearns.jDrafting.geom.point;

import com.brianmearns.jDrafting.geom.scalar.AbstractScalar;
import com.brianmearns.jDrafting.geom.scalar.Scalar;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;

public abstract class AbstractPoint implements Point {

    @Nullable
    private Scalar xScalar;

    @Nullable
    private Scalar yScalar;

    @Override
    @NotNull
    public String toString() {
        return "(" + getX() + ',' + getY() + ')';
    }

    /**
     * Returns the distance between this point and the given point.
     */
    @Override
    public Scalar distance(@NotNull Point from) {
        return TwoPointDistanceScalar.create(from, this);
    }

    /**
     * Returns a {@link Scalar} which is backed by the X coordinate of this Point.
     */
    @Override
    public Scalar getXScalar() {
        if(xScalar == null) {
            xScalar = new AbstractScalar() {
                @Override
                public double getValue() {
                    return AbstractPoint.this.getX();
                }
            };
        }
        return xScalar;
    }

    /**
     * Returns a {@link Scalar} which is backed by the Y coordinate of this Point.
     */
    @Override
    public Scalar getYScalar() {
        if(yScalar == null) {
            yScalar = new AbstractScalar() {
                @Override
                public double getValue() {
                    return AbstractPoint.this.getY();
                }
            };
        }
        return yScalar;
    }

    /**
     * Determines the polar coordinates of the point and returns the angle in radians (counter clockwise of the
     * positive X axis).
     */
    @Override
    public Scalar getThetaScalar() {
        return new AbstractScalar() {
            @Override
            public double getValue() {
                return Math.atan2(getY(), getX());
            }
        };
    }

    /**
     * Determines the polar coordinates of the point and returns the radius of this point from the origin.
     */
    @Override
    public Scalar getRadiusScalar() {
        return distance(Point.origin());
    }

    @Override
    @NotNull
    public Scalar getDeltaXFrom(@NotNull Point from) {
        return getXScalar().subtract(from.getXScalar());
    }

    @Override
    @NotNull
    public Scalar getDeltaYFrom(@NotNull Point from) {
        return getYScalar().subtract(from.getYScalar());
    }

    /**
     * Returns the square of the distance between this point and the next. If this is what you want, it's more
     * efficient than calling {@link #distance(Point)} and squaring the result.
     */
    @Override
    public Scalar distanceSquared(@NotNull Point from) {
        return TwoPointDistanceSquaredScalar.create(from, this);
    }

    /**
     * Resolves the current location of the point and returns a new Point object
     * fixed to this position.
     */
    @Override
    public Point fix() {
        return FixedPoint.fixPoint(this);
    }

    /**
     * Returns a Point which is at a specified Cartesian offset from this point.
     */
    @Override
    public Point translate(@NotNull Scalar x, @NotNull Scalar y) {
        return CoordinatePoint.create(getXScalar().addTo(x), getYScalar().addTo(y));
    }

    /**
     * Returns a Point at the specified dilation from the given reference point.
     */
    @Override
    public Point dilate(@NotNull Scalar scale, @NotNull Point ref) {
        return ref.translate(
                getDeltaXFrom(ref).multiply(scale),
                getDeltaYFrom(ref).multiply(scale)
        );
    }

    /**
     * Return a Point at the specified dilation from the origin.
     */
    @Override
    public Point dilate(@NotNull Scalar scale) {
        return new CoordinatePoint(
                getXScalar().multiply(scale),
                getYScalar().multiply(scale)
        );
    }

    /**
     * Returns a Point at a specified rotation from the given reference point.
     */
    @Override
    public Point rotate(@NotNull Scalar degrees, @NotNull Point pivot) {
        final Scalar dx = pivot.getXScalar();
        final Scalar dy = pivot.getYScalar();
        return translate(dx.negative(), dy.negative())
                .rotate(degrees)
                .translate(dx, dy);
    }

    @Override
    public Point rotate(@NotNull Scalar degrees) {
        return PolarCoordinatePoint.create(getThetaScalar().addTo(degrees), getRadiusScalar());
    }
}

