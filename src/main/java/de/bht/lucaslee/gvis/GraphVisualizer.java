package de.bht.lucaslee.gvis;

import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class GraphVisualizer {

    private final Viewer graphViewer;

    private final ActionListener startButtonListener;
    private final ActionListener backButtonListener;
    private final ActionListener forwardButtonListener;
    private final ActionListener endButtonListener;

    private JFrame frame;

    protected GraphVisualizer(
            Viewer viewer,
            ActionListener startButtonListener,
            ActionListener backButtonListener,
            ActionListener forwardButtonListener,
            ActionListener endButtonListener
    ) {
        this.graphViewer = viewer;
        this.startButtonListener = startButtonListener;
        this.backButtonListener = backButtonListener;
        this.forwardButtonListener = forwardButtonListener;
        this.endButtonListener = endButtonListener;
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        addMainPanel();
        addButtonPanel();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addMainPanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1280, 960);
            }
        };

        graphViewer.enableAutoLayout();
        ViewPanel view = (ViewPanel) graphViewer.addDefaultView(false);
        panel.add(view);
        frame.add(panel);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1280, 40);
            }
        };

        JButton startButton = new JButton("start");
        JButton backButton = new JButton("back");
        JButton forwardButton = new JButton("forward");
        JButton endButton = new JButton("end");

        startButton.addActionListener(startButtonListener);
        backButton.addActionListener(backButtonListener);
        forwardButton.addActionListener(forwardButtonListener);
        endButton.addActionListener(endButtonListener);

        buttonPanel.add(startButton);
        buttonPanel.add(backButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(endButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }
}
