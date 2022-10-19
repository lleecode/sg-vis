package de.bht.lucaslee.gvis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class VisualizedSubgraph {

    private final WeightedGraph graph;
    private final ArrayList<HashSet<WeightedEdge>> state;

    private int visualizedState = 0;

    public VisualizedSubgraph(WeightedGraph graph, Style.Size size, Style.Color color) {
        this.graph = graph;
        this.state = new ArrayList<>();
        state.add(new HashSet<>());
        this.graph.setAttribute(Attributes.STYLESHEET, getStylesheet(size.name(), color.rgb));
        new GraphVisualizer(
                graph.getSwingViewerOfGraph(),
                startButtonListener,
                backButtonListener,
                forwardButtonListener,
                endButtonListener
        );
    }

    public VisualizedSubgraph(WeightedGraph graph, Style.Size size) {
        this(graph, size, Style.Color.VIOLET);
    }

    public VisualizedSubgraph(WeightedGraph graph, Style.Color color) {
        this(graph, Style.Size.M, color);
    }

    public VisualizedSubgraph(WeightedGraph graph) {
        this(graph, Style.Size.M, Style.Color.VIOLET);
    }

    public void addEdgeToSubgraph(WeightedEdge edge) throws InvalidEdgeException {
        if (!graph.containsEdge(edge))
            throw new InvalidEdgeException("Graph does not contain Edge between Nodes with names '"
                    + edge.getNodeA().getName()
                    + "' and '"
                    + edge.getNodeB().getName()
                    + "'. Subgraph can only contain Edges that are part of the Graph.");
        HashSet<WeightedEdge> oldState = state.get(state.size() - 1);
        if (oldState.contains(edge))
            throw new InvalidEdgeException("Edge between Nodes with names '"
                    + edge.getNodeA().getName()
                    + "' and '"
                    + edge.getNodeB().getName()
                    + "'. is already part of sub graph");

        HashSet<WeightedEdge> newState = new HashSet<>(oldState);
        newState.add(edge);
        state.add(newState);
    }

    private void updateVisualization() {
        HashSet<Node> nodes = new HashSet<>();
        graph.getEdges().forEach(e -> {
            if (state.get(visualizedState).contains(e)) {
                graph.setAttribute(e, Attributes.CLASS, "subgraph");
                nodes.add(e.getNodeA());
                nodes.add(e.getNodeB());
            } else graph.removeAttribute(e, Attributes.CLASS);
        });
        graph.getNodes().forEach(n -> {
            if (nodes.contains(n)) graph.setAttribute(n, Attributes.CLASS, "subgraph");
            else graph.removeAttribute(n, Attributes.CLASS);
        });
    }

    private final ActionListener startButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (visualizedState > 0) {
                visualizedState = 0;
                updateVisualization();
            }
        }
    };

    private final ActionListener backButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (visualizedState > 0) {
                visualizedState--;
                updateVisualization();
            }
        }
    };

    private final ActionListener forwardButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (visualizedState < state.size() - 1) {
                visualizedState++;
                updateVisualization();
            }
        }
    };

    private final ActionListener endButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (visualizedState < state.size() - 1) {
                visualizedState = state.size() - 1;
                updateVisualization();
            }
        }
    };

    private static String getStylesheet(String scale, String subgraphColor) {
        return "edge {\n" +
                "    fill-color: black;\n" +
                "    size: 2;\n" +
                "    text-size: 25;\n" +
                "    text-alignment: above;\n" +
                "    text-style: bold;\n" +
                "    text-background-mode: rounded-box;\n" +
                "    text-background-color: white;\n" +
                "    text-padding: 4, 4;\n" +
                "}\n" +
                "edge.subgraph {\n" +
                "    fill-color: " + subgraphColor + ";\n" +
                "}\n" +
                "node {\n" +
                "    text-size: 25;\n" +
                "    stroke-color: black;" +
                "    text-style: bold;\n" +
                "    size: 25, 25;\n" +
                "    stroke-mode: plain;\n" +
                "    stroke-width: 2;\n" +
                "    fill-color: white;\n" +
                "}\n" +
                "node.subgraph {" +
                "   stroke-color: " + subgraphColor + ";" +
                "}\n";
    }
}
