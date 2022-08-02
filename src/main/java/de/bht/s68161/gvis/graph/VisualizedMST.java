package de.bht.s68161.gvis.graph;

import java.util.HashSet;

public class VisualizedMST {
    private final VisualizedGraph graph;
    private final HashSet<WeightedEdge> edges;

    public VisualizedMST(VisualizedGraph graph) {
        this.graph = graph;
        this.edges = new HashSet<>();
        this.graph.setAttribute(Attributes.STYLESHEET.getValue(), STYLESHEET);
    }

    public void addEdgeToMST(WeightedEdge edge) throws InvalidEdgeException {
        if (!graph.containsEdge(edge)) throw new InvalidEdgeException("Edge has to be contained in graph.");
        if (edges.contains(edge)) throw new InvalidEdgeException("Edge is already part of MST");

        edges.add(edge);
        graph.setAttribute(edge, Attributes.CLASS.getValue(), "mst");
    }

    public HashSet<WeightedEdge> getEdges() {
        return new HashSet<>(edges);
    }

    private static final String STYLESHEET = "" +
            "edge {\n" +
            "    fill-color: black;\n" +
            "    size: 2;\n" +
            "    text-size: 25;\n" +
            "    text-alignment: above;\n" +
            "    text-color: black;\n" +
            "    text-style: bold;\n" +
            "    text-background-mode: rounded-box;\n" +
            "    text-background-color: white;\n" +
            "    text-padding: 4, 4;\n" +
            "}\n" +
            "\n" +
            "edge.mst {\n" +
            "    fill-color: red;\n" +
            "}\n" +
            "\n" +
            "node {\n" +
            "    text-size: 25;\n" +
            "    text-style: bold;\n" +
            "    size: 25, 25;\n" +
            "    stroke-mode: plain;\n" +
            "    stroke-width: 2;\n" +
            "    fill-color: white;\n" +
            "}\n";
}
