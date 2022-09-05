package de.bht.lucas.lee.gvis;

import de.bht.lucaslee.gvis.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    @Test
    public void testConstructor() {
        String name = "test name node";
        Node n = new Node(name);
        assertEquals(name, n.getName());
    }

}
