package de.bht.lucas.lee.gvis.graph;

import de.bht.lucaslee.gvis.graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    @Test
    public void testValues() {
        String name = "test name node";
        Node n = new Node(name);
        assertEquals(name, n.getName());
    }

}
