package de.bht.s68161.gvis.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.implementations.SingleGraph;

public class VisualizedGraph {

    private final SingleGraph graph;

    public VisualizedGraph() {
        graph = new SingleGraph("", false, false);
        graph.setAttribute("ui.stylesheet", STYLESHEET);
        graph.display();
    }

    public Node addNode(String name) {
        return Node.from(graph.addNode(name));
    }

    public Node removeNode(String name) throws ElementNotFoundException {
        return Node.from(graph.removeNode(name));
    }

    public Node removeNode(Node node) throws ElementNotFoundException {
        graph.removeNode(node.getName());
        return node;
    }

    public WeightedEdge addEdge(String name, String nodeA, String nodeB, int weight) {
        Edge e = graph.addEdge(name, nodeA, nodeB);
        e.setAttribute("ui.label", weight);
        return new WeightedEdge(e.getId(), Node.from(e.getNode0()), Node.from(e.getNode1()), weight);
    }

    public WeightedEdge removeEdge(WeightedEdge edge) throws ElementNotFoundException {
        graph.removeEdge(edge.getName());
        return edge;
    }

    public WeightedEdge removeEdge(String name) {
        return WeightedEdge.from(graph.removeEdge(name));
    }

    protected boolean containsEdge(WeightedEdge edge) {
        return WeightedEdge.from(graph.getEdge(edge.getName())).equals(edge);
    }



    private static final String STYLESHEET = "" +
            "edge {\n" +
            "    fill-color: black;\n" +
            "    size: 2;\n" +
            "    text-size: 25;\n" +
            "    text-alignment: above;\n" +
            "    text-color: black;\n" +
            "    text-style: bold;\n" +
            "    text-background-mode: rounded-box;\n" +
            "    text-background-color: white;\n" +
            "    text-padding: 4, 4;\n" +
            "}\n" +
            "\n" +
            "edge.mst {\n" +
            "    fill-color: red;\n" +
            "}\n" +
            "\n" +
            "node {\n" +
            "    text-size: 25;\n" +
            "    text-style: bold;\n" +
            "    size: 25, 25;\n" +
            "    stroke-mode: plain;\n" +
            "    stroke-width: 2;\n" +
            "    fill-color: white;\n" +
            "}\n";

}
