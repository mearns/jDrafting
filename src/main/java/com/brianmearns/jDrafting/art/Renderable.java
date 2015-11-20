package com.brianmearns.jDrafting.art;

import com.brianmearns.jDrafting.render.Renderer;

import javax.validation.constraints.NotNull;

/**
 * Represents an object that can be rendered to the image.
 */
public interface Renderable {

    void render(@NotNull Renderer renderer);
}
