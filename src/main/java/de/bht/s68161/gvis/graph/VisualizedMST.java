package de.bht.s68161.gvis.graph;

import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class VisualizedMST {
    private final VisualizedGraph graph;
    private final ArrayList<HashSet<WeightedEdge>> state;
    private int visualizedState = 0;

    private JFrame frame;
    JButton startButton;
    JButton backButton;
    JButton forwardButton;
    JButton endButton;

    public VisualizedMST(VisualizedGraph graph) {
        this.graph = graph;
        this.state = new ArrayList<>();
        state.add(new HashSet<>());
        this.graph.setAttribute(Attributes.STYLESHEET.getValue(), STYLESHEET);
        initializeFrame();
    }

    public void addEdgeToMST(WeightedEdge edge) throws InvalidEdgeException {
        if (!graph.containsEdge(edge)) throw new InvalidEdgeException("Edge has to be contained in graph.");
        HashSet<WeightedEdge> oldState = state.get(state.size() - 1);
        if (oldState.contains(edge)) throw new InvalidEdgeException("Edge is already part of MST");

        HashSet<WeightedEdge> newState = new HashSet<>(oldState);
        newState.add(edge);
        state.add(newState);
    }

    public void updateVisualization() {

        System.out.println("Printing edges of visualized state " + visualizedState);

        graph.getEdges().forEach(e -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (state.get(visualizedState).contains(e)) {
                System.out.println("Contained Edge: " + e.getName());
                graph.setAttribute(e, Attributes.CLASS.getValue(), "mst");
            } else {
                System.out.println("Edge not contained: " + e.getName());
                graph.removeAttribute(e, Attributes.CLASS.getValue());
            }
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

    private void initializeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(createGraphPanel());
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createGraphPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };

        Viewer viewer = new SwingViewer(graph.getGraph(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        ViewPanel view = (ViewPanel) viewer.addDefaultView(false);
        panel.add(view);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 40);
            }
        };

        startButton = new JButton("start");
        backButton = new JButton("back");
        forwardButton = new JButton("forward");
        endButton = new JButton("end");

        startButton.addActionListener(startButtonListener);
        backButton.addActionListener(backButtonListener);
        forwardButton.addActionListener(forwardButtonListener);
        endButton.addActionListener(endButtonListener);

        buttonPanel.add(startButton);
        buttonPanel.add(backButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(endButton);

        return buttonPanel;
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

    private void updateButtons() {
        startButton.setEnabled(true);
        backButton.setEnabled(true);
        forwardButton.setEnabled(true);
        endButton.setEnabled(true);

        if (visualizedState == 0) {
            startButton.setEnabled(false);
            backButton.setEnabled(false);
        }
        if (visualizedState == state.size() - 1) {
            endButton.setEnabled(false);
            forwardButton.setEnabled(false);
        }
    }

}
