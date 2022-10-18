package de.bht.lucaslee.gvis;

import org.graphstream.graph.Edge;

import java.util.Objects;

public class WeightedEdge {

    private final Node nodeA;
    private final Node nodeB;
    private final int weight;

    public WeightedEdge(Node nodeA, Node nodeB, int weight) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.weight = weight;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public int getWeight() {
        return weight;
    }

    protected static WeightedEdge from(Edge edge) {
        int weight;
        try {
            weight = Integer.parseInt(edge.getAttribute(Attributes.LABEL).toString());
        } catch (NumberFormatException nfe) {
            weight = 1;
        }
        return new WeightedEdge(
                Node.from(edge.getNode0()),
                Node.from(edge.getNode1()),
                weight
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedEdge that = (WeightedEdge) o;
        if (weight != that.weight) return false;
        if (Objects.equals(nodeA, that.getNodeA()) && Objects.equals(nodeB, that.getNodeB()))
            return true;
        if (Objects.equals(nodeA, that.getNodeB()) && Objects.equals(nodeB, that.getNodeA()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeA, nodeB, weight);
    }
}
