package com.brianmearns.jDrafting.render;

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

import static org.junit.Assert.*;

public class SvgRendererTest {

    private static final String svgNamespaceUri = "http://www.w3.org/2000/svg";

    @NotNull
    private SvgRenderer renderer;

    @Before
    public void setUp() {
        renderer = new SvgRenderer();
    }

    @NotNull
    protected Element verifySvgElement(Node node, @NotNull String tagName, @NotNull Map<String, Object> attributes) {
        assertNotNull("Node should not be null.", node);
        assertEquals("Node should be of type element (" + Node.ELEMENT_NODE + ")", Node.ELEMENT_NODE, node.getNodeType());

        final Element element = (Element)node;
        assertEquals("Element should have SVG Namespace.", svgNamespaceUri, element.lookupNamespaceURI(null));
        assertEquals("Element has wrong tag name.", tagName, element.getTagName());

        for(Map.Entry<String, Object> entry : attributes.entrySet()) {
            assertEquals("Element has wrong value for \"" + entry.getKey() + "\" attribute.", String.valueOf(entry.getValue()), element.getAttribute(entry.getKey()));
        }

        return element;
    }

    @Test
    public void test_ellipse() {
        SvgRenderer res = renderer.ellipse(null, 10, 5.5, 22.5, 24.25);

        assertSame("Expected ellipse to be reflexive.", renderer, res);

        final XMLBuilder2 builder = res.getBuilder();
        assertNotNull("Renderer's builder should not be null.", builder);

        //builder.toWriter(new PrintWriter(System.out), null);

        final Document doc = builder.getDocument();
        final Element docEle = doc.getDocumentElement();
        assertEquals("Document element should be SVG.", "svg", docEle.getTagName());
        assertEquals("SVG element should have SVG Namespace for the default namespace (\"xmlns\" attribute).",
            svgNamespaceUri, docEle.lookupNamespaceURI(null));
        assertEquals("SVG element should have SVG Namespace for the \"svg\" xmlns.", svgNamespaceUri, docEle.getAttribute("xmlns:svg"));
        
        final NodeList elements = docEle.getElementsByTagNameNS(svgNamespaceUri, "ellipse");
        assertEquals("SVG element should only have one descendent \"ellipse\" element.", 1, elements.getLength());

        final Node node = elements.item(0);
        final Element element = verifySvgElement(node, "ellipse", new ImmutableMap.Builder<String, Object>()
            .put("cx", 10.0)
            .put("cy", 5.5)
            .put("rx", 22.5)
            .put("ry", 24.25)
            .build());
    }

}

