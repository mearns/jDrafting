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
import java.util.Random;

import static org.junit.Assert.*;

public class SvgRendererTest {

    private static final String svgNamespaceUri = "http://www.w3.org/2000/svg";

    @NotNull
    private SvgRenderer renderer;

    @NotNull
    private Random rand;

    @Before
    public void setUp() {
        renderer = new SvgRenderer();
        rand = null;
    }

    protected void seed(long seed) {
        rand = new Random(seed);
    }

    @NotNull
    protected SvgRenderer getRenderer() {
        return renderer;
    }

    @NotNull
    protected Random getRand() {
        if(rand == null) {
            throw new NullPointerException("Random hasn't been initialized yet. Use seed() at the start of the test method.");
        }
        return rand;
    }

    protected double nextDouble() {
        return Double.MAX_VALUE * getRand().nextDouble();
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

    /**
     * Verifies the root element is a properly formed SVG element, and returns the document element.
     */
    @NotNull
    protected Element verifySvgDocumentElement() {
        final XMLBuilder2 builder = renderer.getBuilder();
        assertNotNull("Renderer's builder should not be null.", builder);

        //builder.toWriter(new PrintWriter(System.out), null);

        final Document doc = builder.getDocument();
        final Element docEle = doc.getDocumentElement();
        assertEquals("Document element should be SVG.", "svg", docEle.getTagName());
        assertEquals("SVG element should have SVG Namespace for the default namespace (\"xmlns\" attribute).",
            svgNamespaceUri, docEle.lookupNamespaceURI(null));
        assertEquals("SVG element should have SVG Namespace for the \"svg\" xmlns.", svgNamespaceUri, docEle.getAttribute("xmlns:svg"));

        return docEle;
    }

    /**
     * After calling {@link verifySvgDocumentElement()}, gets a list of elements with the given tagname, verifies that
     * there is only one result, and verifies that it has the given attributes.
     *
     * <p>
     * Returns the element in question.
     */
    @NotNull Element verifySingleSvgElement(@NotNull String tagName, @NotNull Map<String, Object> attributes) {
        final Element docEle = verifySvgDocumentElement();
        final NodeList elements = docEle.getElementsByTagNameNS(svgNamespaceUri, tagName);
        assertEquals("SVG element should only have one descendent \"" + tagName + "\" element.", 1, elements.getLength());

        final Node node = elements.item(0);
        assertSame("Element should be a direct child of the root SVG element. Wrong parent found.", docEle, node.getParentNode());

        return verifySvgElement(node, tagName, attributes);
    }

    @Test
    public void test_ellipse() {
        seed(1);
        final double cx = nextDouble();
        final double cy = nextDouble();
        final double rx = nextDouble();
        final double ry = nextDouble();
        SvgRenderer res = renderer.ellipse(null, cx, cy, rx, ry);
        assertSame("Expected ellipse to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("ellipse", new ImmutableMap.Builder<String, Object>()
            .put("cx", cx)
            .put("cy", cy)
            .put("rx", rx)
            .put("ry", ry)
            .build());

        assertEquals("Ellipse element has wrong number of children.", 0, element.getChildNodes().getLength());
    }

    @Test
    public void test_circle() {
        seed(2);
        final double cx = nextDouble();
        final double cy = nextDouble();
        final double radius = nextDouble();

        SvgRenderer res = renderer.circle(null, cx, cy, radius);
        assertSame("Expected circle to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("circle", new ImmutableMap.Builder<String, Object>()
            .put("cx", cx)
            .put("cy", cy)
            .put("r", radius)
            .build());

        assertEquals("Circle element has wrong number of children.", 0, element.getChildNodes().getLength());
    }

    @Test
    public void test_line() {
        seed(3);
        final double sx = nextDouble();
        final double sy = nextDouble();
        final double ex = nextDouble();
        final double ey = nextDouble();

        SvgRenderer res = renderer.line(null, sx, sy, ex, ey);
        assertSame("Expected line to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("line", new ImmutableMap.Builder<String, Object>()
            .put("x1", sx)
            .put("y1", sy)
            .put("x2", ex)
            .put("y2", ey)
            .build());

        assertEquals("Line element has wrong number of children.", 0, element.getChildNodes().getLength());
    }

    @Test
    public void test_rect() {
        seed(4);
        final double x = nextDouble();
        final double y = nextDouble();
        final double width = nextDouble();
        final double height = nextDouble();

        SvgRenderer res = renderer.rect(null, x, y, width, height);
        assertSame("Expected rect to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("rect", new ImmutableMap.Builder<String, Object>()
            .put("x", x)
            .put("y", y)
            .put("width", width)
            .put("height", height)
            .build());

        assertEquals("Rect element has wrong number of children.", 0, element.getChildNodes().getLength());
    }

    @Test
    public void test_rectWithRoundCorners() {
        seed(5);
        final double x = nextDouble();
        final double y = nextDouble();
        final double width = nextDouble();
        final double height = nextDouble();
        final double rx = nextDouble();
        final double ry = nextDouble();

        SvgRenderer res = renderer.rect(null, x, y, width, height, rx, ry);
        assertSame("Expected rect to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("rect", new ImmutableMap.Builder<String, Object>()
            .put("x", x)
            .put("y", y)
            .put("width", width)
            .put("height", height)
            .put("rx", rx)
            .put("ry", ry)
            .build());

        assertEquals("Rect element has wrong number of children.", 0, element.getChildNodes().getLength());
    }
    @Test
    public void test_text() {
        seed(6);
        final double x = nextDouble();
        final double y = nextDouble();
        final String text = "This is a test string: " + nextDouble();

        SvgRenderer res = renderer.text(null, x, y, text);
        assertSame("Expected rect to be reflexive.", renderer, res);

        final Element element = verifySingleSvgElement("text", new ImmutableMap.Builder<String, Object>()
            .put("x", x)
            .put("y", y)
            .build());

        assertEquals("Text element has wrong number of children.", 1, element.getChildNodes().getLength());

        final Node textNode = element.getFirstChild();
        assertEquals("Child of text element should be a text node (" + Node.TEXT_NODE + "). Type is wrong.",
            Node.TEXT_NODE, textNode.getNodeType());
        assertEquals("Text element has wrong text child.", text, textNode.getNodeValue());
    }

}

