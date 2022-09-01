package de.bht.lucaslee.gvis.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeightedGraph {

    private final SingleGraph graph;

    public WeightedGraph() {
        graph = new SingleGraph("0", true, false);
    }

    public Node addNode(String name) {
        try {
            org.graphstream.graph.Node node = graph.addNode(name);
            node.setAttribute(Attributes.LABEL, node.getId());
            return Node.from(node);
        } catch (IdAlreadyInUseException ex) {
            throw new InvalidNodeException("Graph already contains a Node with the name: " + name);
        }
    }

    public Node addNode(Node node) {
        return addNode(node.getName());
    }

    public Node removeNode(String name) {
        try {
            return Node.from(graph.removeNode(name));
        } catch (ElementNotFoundException ex) {
            throw new InvalidNodeException("Cannot remove Node. Graph does not contain Node with name \"" + name + "\"");
        }
    }

    public Node removeNode(Node node) {
        return removeNode(node.getName());
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
        return getEdgesStream().collect(Collectors.toCollection(HashSet::new));
    }

    private Stream<WeightedEdge> getEdgesStream() {
        return graph.edges().map(WeightedEdge::from);
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeightedGraph (");
        getEdgesStream().forEach(e -> {
            sb.append("\n\t");
            sb.append(e.getNodeA().getName());
            sb.append(" --- ");
            sb.append(e.getWeight());
            sb.append(" --- ");
            sb.append(e.getNodeB().getName());
        });
        sb.append("\n)");
        return sb.toString();
    }
}
