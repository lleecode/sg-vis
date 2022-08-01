package de.bht.s68161.gvis.graph;

import java.util.HashSet;

public class VisualizedMST {
    private final VisualizedGraph graph;
    private final HashSet<WeightedEdge> edges;

    public VisualizedMST(VisualizedGraph graph) {
        this.graph = graph;
        this.edges = new HashSet<>();
    }

    public void addEdgeToMST(WeightedEdge edge) throws InvalidEdgeException {
        if(!graph.containsEdge(edge)) throw new InvalidEdgeException("Edge has to be contained in graph.");
        if(edges.contains(edge)) throw new InvalidEdgeException("Edge is already part of MST");

        edges.add(edge);

        // set attr class to mst
        // .setAttribute("ui.class", "mst");
    }
}
