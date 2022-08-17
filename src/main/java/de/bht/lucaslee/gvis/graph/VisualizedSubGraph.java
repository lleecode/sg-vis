package de.bht.lucaslee.gvis.graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class visualizes a graph and a related sub graph.
 *
 * @author Lucas Lee
 * @version 0.9
 * @since 2022-08-16
 */

public class VisualizedSubGraph {

    private final WeightedGraph graph;
    private final ArrayList<HashSet<WeightedEdge>> state;

    private int visualizedState = 0;

    public VisualizedSubGraph(WeightedGraph graph) {
        this.graph = graph;
        this.state = new ArrayList<>();
        state.add(new HashSet<>());
        this.graph.setAttribute(Attributes.STYLESHEET, STYLESHEET);
        new GraphVisualizer(
                graph.getSwingViewerOfGraph(),
                startButtonListener,
                backButtonListener,
                forwardButtonListener,
                endButtonListener
        );
    }

    public void addEdgeToSubGraph(WeightedEdge edge) throws InvalidEdgeException {
        if (!graph.containsEdge(edge)) throw new InvalidEdgeException("Edge has to be contained in graph.");
        HashSet<WeightedEdge> oldState = state.get(state.size() - 1);
        if (oldState.contains(edge)) throw new InvalidEdgeException("Edge is already part of sub graph");

        HashSet<WeightedEdge> newState = new HashSet<>(oldState);
        newState.add(edge);
        state.add(newState);
    }

    public void updateVisualization() {
        graph.getEdges().forEach(e -> {
            if (state.get(visualizedState).contains(e))
                graph.setAttribute(e, Attributes.CLASS, "subgraph");
            else graph.removeAttribute(e, Attributes.CLASS);
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
            "edge.subgraph {\n" +
            "    fill-color: blue;\n" +
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
