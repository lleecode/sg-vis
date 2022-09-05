package de.bht.lucas.lee.gvis;

import de.bht.lucaslee.gvis.InvalidEdgeException;
import de.bht.lucaslee.gvis.InvalidNodeException;
import de.bht.lucaslee.gvis.Node;
import de.bht.lucaslee.gvis.WeightedEdge;
import de.bht.lucaslee.gvis.WeightedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightedGraphTest {

    @Test
    void testConstructor() {
        WeightedGraph graph = new WeightedGraph();
        assertEquals(0, graph.getEdges().size());
        assertEquals(0, graph.getNodes().size());
        assertEquals("WeightedGraph (\n\tNodes:\n\t\t\n\tEdges:\n)", graph.toString());
    }

    @Test
    void testNodes() {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("node a");
        graph.addNode(new Node("node b"));
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

    @Test
    void testEdges() {
        WeightedGraph graph = new WeightedGraph();
        graph.addNode("node a");
        graph.addNode("node b");
        graph.addNode("node c");
        graph.addEdge("edge a", "node a", "node b", 5);
        graph.addEdge("edge b", "node a", "node c", 9);
        graph.addEdge("edge c", "node b", "node c", 3);
        assertEquals(3, graph.getNodes().size());
        assertEquals(3, graph.getEdges().size());

        graph.removeEdge(new WeightedEdge(
                "edge c",
                new Node("node b"),
                new Node("node c"),
                3
        ));
        assertEquals(2, graph.getEdges().size());

        graph.removeNode("node a");
        graph.removeNode("node b");
        assertEquals(0, graph.getEdges().size());

        assertThrows(InvalidEdgeException.class, () -> {
            graph.addEdge("edge d", "node x", "node b", 1);
        });
        assertThrows(InvalidEdgeException.class, () -> {
            graph.removeEdge("edge x");
        });
    }
}
