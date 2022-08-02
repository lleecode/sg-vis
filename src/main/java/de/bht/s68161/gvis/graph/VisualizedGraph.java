package de.bht.s68161.gvis.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.implementations.SingleGraph;

public class VisualizedGraph {

    private final SingleGraph graph;

    public VisualizedGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("0", true, false);
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
        e.setAttribute(Attributes.LABEL.getValue(), weight);
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

    protected void setAttribute(WeightedEdge edge, String attr, String value) {
        Edge e = graph.getEdge(edge.getName());
        e.setAttribute(attr, value);
    }

    protected void setAttribute(Node node, String attr, String value) {
        org.graphstream.graph.Node n = graph.getNode(node.getName());
        n.setAttribute(attr, value);
    }

    protected void setAttribute(String attr, String value) {
        graph.setAttribute(attr, value);
    }
}