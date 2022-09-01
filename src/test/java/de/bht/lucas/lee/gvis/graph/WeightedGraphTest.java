package de.bht.lucas.lee.gvis.graph;

import de.bht.lucaslee.gvis.graph.InvalidNodeException;
import de.bht.lucaslee.gvis.graph.WeightedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightedGraphTest {

    @Test
    void testConstructor() {
        WeightedGraph graph = new WeightedGraph();
        assertEquals(0, graph.getEdges().size());
        assertEquals(0, graph.getNodes().size());
        assertEquals("WeightedGraph (\n)", graph.toString());
    }

    @Test
    void testNodes() {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("node a");
        graph.addNode("node b");
        graph.addNode("node c");

        assertEquals(3, graph.getNodes().size());

        assertThrows(InvalidNodeException.class, () -> {
            graph.addNode("node c");
        });

        graph.removeNode("node a");

        assertEquals(2, graph.getNodes().size());

        assertThrows(InvalidNodeException.class, () -> {
            graph.removeNode("unknown node");
        });
    }
}
