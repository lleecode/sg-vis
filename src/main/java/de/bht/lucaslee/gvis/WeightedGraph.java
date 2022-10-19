package de.bht.lucaslee.gvis;

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

    public void addNode(String name) {
        if (graph.getNode(name) != null)
            throw new InvalidNodeException("Cannot add Node with name '"
                    + name + "'. Name already in use.");
        org.graphstream.graph.Node node = graph.addNode(name);
        node.setAttribute(Attributes.LABEL, node.getId());
    }

    public void removeNode(String name) {
        if (graph.getNode(name) == null)
            throw new InvalidNodeException("Cannot remove Node with name '"
                    + name + "'. Graph does not contain a Node with this name.");
        graph.removeNode(name);
    }

    public HashSet<Node> getNodes() {
        return this.getNodesStream().collect(Collectors.toCollection(HashSet::new));
    }

    private Stream<Node> getNodesStream() {
        return graph.nodes().map(Node::from);
    }

    public void addEdge(String nodeA, String nodeB, int weight) {
        if (graph.getNode(nodeA).hasEdgeBetween(nodeB))
            throw new InvalidEdgeException("Cannot add Edge between Nodes with names '"
                    + nodeA + "' and '" + nodeB + "'. Edge already contained");
        Edge e = graph.addEdge(nodeA + nodeB, nodeA, nodeB);
        e.setAttribute(Attributes.LABEL, weight);
    }

    public void removeEdge(String nodeA, String nodeB) {
        if (graph.getNode(nodeA) == null)
            throw new InvalidNodeException("Cannot remove Edge between Nodes with names'"
                    + nodeA + "' and '" + nodeB + "'."
                    + " Graph does not contain Node with name '" + nodeA + "'");
        if (graph.getNode(nodeB) == null)
            throw new InvalidNodeException("Cannot remove Edge between Nodes with names'"
                    + nodeA + "' and '" + nodeB + "'."
                    + " Graph does not contain Node with name '" + nodeB + "'");
        graph.removeEdge(nodeA, nodeB);
    }

    public HashSet<WeightedEdge> getEdges() {
        return this.getEdgesStream().collect(Collectors.toCollection(HashSet::new));
    }

    public HashSet<Node> getNeighbors(String node) {
        org.graphstream.graph.Node n = graph.getNode(node);
        if (n == null)
            throw new InvalidNodeException("Graph does not contain Node with name '" + node + "'");
        return n.neighborNodes().map(Node::from).collect(Collectors.toCollection(HashSet::new));
    }

    private Stream<WeightedEdge> getEdgesStream() {
        return graph.edges().map(WeightedEdge::from);
    }

    protected boolean containsEdge(WeightedEdge edge) {
        org.graphstream.graph.Node nodeA = graph.getNode(edge.getNodeA().getName());
        if (nodeA == null) return false;
        return nodeA.hasEdgeBetween(edge.getNodeB().getName());
    }

    protected void setAttribute(WeightedEdge edge, String attr, String value) {
        Edge e = graph.getNode(edge.getNodeA().getName()).getEdgeBetween(edge.getNodeB().getName());
        e.setAttribute(attr, value);
    }

    protected void removeAttribute(WeightedEdge edge, String attr) {
        Edge e = graph.getNode(edge.getNodeA().getName()).getEdgeBetween(edge.getNodeB().getName());
        e.removeAttribute(attr);
    }

    protected void setAttribute(Node node, String attr, String value) {
        org.graphstream.graph.Node n = graph.getNode(node.getName());
        n.setAttribute(attr, value);
    }

    protected void removeAttribute(Node node, String attr) {
        org.graphstream.graph.Node n = graph.getNode(node.getName());
        n.removeAttribute(attr);
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
        sb.append("\n\tNodes:\n\t\t");
        sb.append(this.getNodesStream().map(Node::getName).collect(Collectors.joining(", ")));
        sb.append("\n\tEdges:");
        this.getEdgesStream().forEach(e -> {
            sb.append("\n\t\t");
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
