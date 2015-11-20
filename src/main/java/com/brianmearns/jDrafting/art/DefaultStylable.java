package com.brianmearns.jDrafting.art;

import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class DefaultStylable implements Stylable {

    @Nullable
    private Style style;

    public DefaultStylable(@Nullable Style style) {
        setStyle(style);
    }

    public DefaultStylable() {
        this(null);
    }

    @Override
    public void setStyle(@Nullable Style style) {
        this.style = style;
    }

    @Nullable
    public Style getStyle() {
        return style;
    }
}
