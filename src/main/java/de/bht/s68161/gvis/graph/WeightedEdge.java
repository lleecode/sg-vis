package de.bht.s68161.gvis.graph;

import org.graphstream.graph.Edge;

import java.util.Objects;

public class WeightedEdge {

    private final String name;
    private final Node nodeA;
    private final Node nodeB;
    private final int weight;


    public WeightedEdge(String name, Node nodeA, Node nodeB, int weight) {
        this.name = name;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.weight = weight;
    }

    public String getName() {
        return name;
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
            weight = Integer.parseInt(edge.getAttribute("ui.label").toString());
        } catch (NumberFormatException nfe) {
            weight = 1;
        }
        return new WeightedEdge(
                edge.getId(),
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
        return (weight == that.weight)
                && (Objects.equals(name, that.name))
                && (nodeA.getName().equals(that.nodeA.getName()) && nodeB.getName().equals(that.nodeB.getName()) ||
                nodeA.getName().equals(that.nodeB.getName()) && nodeB.getName().equals(that.nodeA.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nodeA, nodeB, weight);
    }
}
