package de.bht.s68161.gvis.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.stream.Collectors;

public class VisualizedGraph {

    private final SingleGraph graph;

    public VisualizedGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("0", true, false);
        graph.display();
    }

    public Node addNode(String name) {
        org.graphstream.graph.Node node = graph.addNode(name);
        node.setAttribute(Attributes.LABEL.getValue(), node.getId());
        return Node.from(node);
    }

    protected Node removeNode(String name) throws ElementNotFoundException {
        return Node.from(graph.removeNode(name));
    }

    protected Node removeNode(Node node) throws ElementNotFoundException {
        graph.removeNode(node.getName());
        return node;
    }

    public HashSet<Node> getNodes() {
        return graph.nodes().map(Node::from).collect(Collectors.toCollection(HashSet::new));
    }

    public WeightedEdge addEdge(String name, String nodeA, String nodeB, int weight) {
        Edge e = graph.addEdge(name, nodeA, nodeB);
        e.setAttribute(Attributes.LABEL.getValue(), weight);
        return new WeightedEdge(e.getId(), Node.from(e.getNode0()), Node.from(e.getNode1()), weight);
    }

    protected WeightedEdge removeEdge(WeightedEdge edge) throws ElementNotFoundException {
        graph.removeEdge(edge.getName());
        return edge;
    }

    protected WeightedEdge removeEdge(String name) {
        return WeightedEdge.from(graph.removeEdge(name));
    }

    public HashSet<WeightedEdge> getEdges() {
        return graph.edges().map(WeightedEdge::from).collect(Collectors.toCollection(HashSet::new));
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
