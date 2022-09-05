package de.bht.lucas.lee.gvis;

import de.bht.lucaslee.gvis.Node;
import de.bht.lucaslee.gvis.WeightedEdge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeightedEdgeTest {

    @Test
    void testConstructor() {
        String name = "test name edge";
        Node nodeA = new Node("Node A");
        Node nodeB = new Node("Node B");
        int weight = 10;
        WeightedEdge edge = new WeightedEdge(name, nodeA, nodeB, weight);

        assertEquals(name, edge.getName());
        assertEquals(nodeA, edge.getNodeA());
        assertEquals(nodeB, edge.getNodeB());
        assertEquals(weight, edge.getWeight());
    }

    @Test
    void testEquality() {
        WeightedEdge edge0
                = new WeightedEdge("0", new Node("a"), new Node("b"), 10);
        WeightedEdge edge0copy
                = new WeightedEdge("0", new Node("b"), new Node("a"), 10);
        WeightedEdge edge1
                = new WeightedEdge("1", new Node("a"), new Node("b"), 10);
        WeightedEdge edge2
                = new WeightedEdge("2", new Node("x"), new Node("y"), 42);

        assertEquals(edge0, edge0copy);
        assertNotEquals(edge0, edge1);
        assertNotEquals(edge0, edge2);
    }
}
