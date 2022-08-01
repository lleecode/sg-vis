package de.bht.s68161.gvis;

import de.bht.s68161.gvis.graph.VisualizedGraph;
import de.bht.s68161.gvis.graph.VisualizedMST;

public class GraphVisualizer {

    private static final int SLEEP_DURATION = 50;

    public static void main(String[] args) throws InterruptedException {
        initVisualizedGraph();
    }

    private static void initVisualizedGraph() {
        VisualizedGraph vGraph = new VisualizedGraph();
        VisualizedMST vMST = new VisualizedMST(vGraph);
    }
}
