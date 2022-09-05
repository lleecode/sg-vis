package de.bht.lucas.lee.gvis;

import de.bht.lucaslee.gvis.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NodeTest {

    @Test
    public void testConstructor() {
        String name = "test name node";
        Node n = new Node(name);
        assertEquals(name, n.getName());
    }

    @Test
    public void testEquality() {
        Node n0 = new Node("0");
        Node n0copy = new Node("0");
        Node n1 = new Node("1");
        assertEquals(n0, n0copy);
        assertNotEquals(n0, n1);
    }

}
