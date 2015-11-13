package com.brianmearns.jDrafting.geom;

public abstract class AbstractPoint implements Point {


    @Override
    @NotNull
    public String toString() {
        return '(' + getX() + ',' + getY() + ')';
    }

}

