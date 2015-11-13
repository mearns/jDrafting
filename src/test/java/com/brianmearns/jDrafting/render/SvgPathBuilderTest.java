package com.brianmearns.jDrafting.render;

import com.brianmearns.jDrafting.testing.RandomTestCase;

import org.junit.Test;
import org.junit.Before;

import com.jamesmurty.utils.XMLBuilder2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.ImmutableMap;

import javax.validation.constraints.NotNull;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

public class SvgPathBuilderTest extends RandomTestCase {

    @NotNull
    private SvgRenderer.SvgPathBuilder uut;

    @NotNull
    private SvgRenderer renderer;

    @Before
    public void setUp() {
        renderer = new SvgRenderer();
        uut = renderer.path(null);
    }

}

