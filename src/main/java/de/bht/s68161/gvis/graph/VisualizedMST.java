package de.bht.s68161.gvis.graph;

import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;

public class VisualizedMST {
    private final VisualizedGraph graph;
    private final ArrayList<HashSet<WeightedEdge>> state;
    private int visualizedState = 0;

    public VisualizedMST(VisualizedGraph graph) {
        this.graph = graph;
        this.state = new ArrayList<>();
        state.add(new HashSet<>());
        this.graph.setAttribute(Attributes.STYLESHEET.getValue(), STYLESHEET);
        setupVisualization();
    }

    public void addEdgeToMST(WeightedEdge edge) throws InvalidEdgeException {
        if (!graph.containsEdge(edge)) throw new InvalidEdgeException("Edge has to be contained in graph.");
        HashSet<WeightedEdge> oldState = state.get(state.size() - 1);
        if (oldState.contains(edge)) throw new InvalidEdgeException("Edge is already part of MST");

        HashSet<WeightedEdge> newState = new HashSet<>(oldState);
        newState.add(edge);
        state.add(newState);
    }

    public void showState(int index) {
        graph.getEdges().forEach(e -> {
            graph.removeAttribute(e, Attributes.CLASS.getValue());
        });
        state.get(index).forEach(e -> {
            graph.setAttribute(e, Attributes.CLASS.getValue(), "mst");
        });
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

    private void setupVisualization() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };
        panel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        Viewer viewer = new SwingViewer(graph.getGraph(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = (ViewPanel) viewer.addDefaultView(false);
        panel.add(view, BorderLayout.NORTH);
        JButton b0 = new JButton("start");
        JButton b1 = new JButton("back");
        JButton b2 = new JButton("forward");
        JButton b3 = new JButton("end");

        panel.add(b0);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        showState(visualizedState);
    }
}
