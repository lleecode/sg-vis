package de.bht.s68161.gvis.graph;

public class Node {
    private final String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected static Node from(org.graphstream.graph.Node n) {
        return new Node(n.getId());
    }
}
