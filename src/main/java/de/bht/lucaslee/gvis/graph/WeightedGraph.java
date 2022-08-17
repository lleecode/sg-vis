package de.bht.lucaslee.gvis.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import java.util.HashSet;
import java.util.stream.Collectors;

public class WeightedGraph {

    private final SingleGraph graph;

    public WeightedGraph() {
        graph = new SingleGraph("0", true, false);
    }

    public Node addNode(String name) {
        org.graphstream.graph.Node node = graph.addNode(name);
        node.setAttribute(Attributes.LABEL, node.getId());
        return Node.from(node);
    }

    public Node addNode(Node node) {
        org.graphstream.graph.Node node_ = graph.addNode(node.getName());
        node_.setAttribute(Attributes.LABEL, node_.getId());
        return node;
    }

    public Node removeNode(String name) throws ElementNotFoundException {
        return Node.from(graph.removeNode(name));
    }

    public Node removeNode(Node node) throws ElementNotFoundException {
        graph.removeNode(node.getName());
        return node;
    }

    public HashSet<Node> getNodes() {
        return graph.nodes().map(Node::from).collect(Collectors.toCollection(HashSet::new));
    }

    public WeightedEdge addEdge(String name, String nodeA, String nodeB, int weight) {
        Edge e = graph.addEdge(name, nodeA, nodeB);
        e.setAttribute(Attributes.LABEL, weight);
        return new WeightedEdge(e.getId(), Node.from(e.getNode0()), Node.from(e.getNode1()), weight);
    }

    public WeightedEdge addEdge(WeightedEdge edge) {
        Edge edge_ = graph.addEdge(
                edge.getName(),
                edge.getNodeA().getName(),
                edge.getNodeB().getName()
        );
        edge_.setAttribute(Attributes.LABEL, edge.getWeight());
        return edge;
    }

    public WeightedEdge removeEdge(WeightedEdge edge) throws ElementNotFoundException {
        graph.removeEdge(edge.getName());
        return edge;
    }

    public WeightedEdge removeEdge(String name) {
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

    protected void removeAttribute(WeightedEdge edge, String attr) {
        Edge e = graph.getEdge(edge.getName());
        e.removeAttribute(attr);
    }

    protected void setAttribute(Node node, String attr, String value) {
        org.graphstream.graph.Node n = graph.getNode(node.getName());
        n.setAttribute(attr, value);
    }

    protected void setAttribute(String attr, String value) {
        graph.setAttribute(attr, value);
    }

    protected Viewer getSwingViewerOfGraph() {
        return new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
    }
}
