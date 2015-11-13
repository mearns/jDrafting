package com.brianmearns.jDrafting.shape;

public interface Shape {

    <R extends Renderer> R render(R renderer);

}

