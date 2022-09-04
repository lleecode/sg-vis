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
            throw new InvalidNodeException(
                    "Graph already contains a node with the name: " + name
            );
        }
    }

    public Node addNode(Node node) {
        return addNode(node.getName());
    }

    public Node removeNode(String name) {
        try {
            return Node.from(graph.removeNode(name));
        } catch (ElementNotFoundException ex) {
            throw new InvalidNodeException(
                    "Cannot remove node. Graph does not contain node with name: " + name
            );
        }
    }

    public Node removeNode(Node node) {
        return this.removeNode(node.getName());
    }

    public HashSet<Node> getNodes() {
        return this.getNodesStream().collect(Collectors.toCollection(HashSet::new));
    }

    private Stream<Node> getNodesStream() {
        return graph.nodes().map(Node::from);
    }

    public WeightedEdge addEdge(String name, String nodeA, String nodeB, int weight) {
        try {
            Edge e = graph.addEdge(name, nodeA, nodeB);
            e.setAttribute(Attributes.LABEL, weight);
            return new WeightedEdge(
                    e.getId(),
                    Node.from(e.getNode0()),
                    Node.from(e.getNode1()),
                    weight
            );
        } catch (ElementNotFoundException ex) {
            throw new InvalidEdgeException(
                    "Cannot add edge. Depended element not found: " + ex.getMessage()
            );
        }
    }

    public WeightedEdge addEdge(WeightedEdge edge) {
        return this.addEdge(
                edge.getName(),
                edge.getNodeA().getName(),
                edge.getNodeB().getName(),
                edge.getWeight()
        );
    }

    public WeightedEdge removeEdge(WeightedEdge edge) throws ElementNotFoundException {
        return this.removeEdge(edge.getName());
    }

    public WeightedEdge removeEdge(String name) {
        try {
            return WeightedEdge.from(graph.removeEdge(name));
        } catch (ElementNotFoundException ex) {
            throw new InvalidEdgeException(
                    "Cannot remove edge. Graph does not contain edge with name: " + name
            );
        }
    }

    public HashSet<WeightedEdge> getEdges() {
        return this.getEdgesStream().collect(Collectors.toCollection(HashSet::new));
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
