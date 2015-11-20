package com.brianmearns.jDrafting.shape;

import com.brianmearns.jDrafting.render.Renderer;

public interface Shape {

    <R extends Renderer> R render(R renderer);

}

