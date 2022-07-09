package de.bht.s68161.gvis;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.util.concurrent.ThreadLocalRandom;


public class GraphVisualizer {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.graphstream.ui", "swing");
        buildRandomGraph(10);
    }


    private static SingleGraph buildRandomGraph(int size) throws InterruptedException {

        SingleGraph graph = new SingleGraph("0", false, false);
        Viewer v = graph.display();
        for (int i = 0; i < size; i++) {
            Thread.sleep(200);
            graph.addNode("" + i);
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(size / 2, size); i++) {
            Thread.sleep(200);
            graph.addEdge(
                    i + "",
                    ThreadLocalRandom.current().nextInt(0, size) + "",
                    ThreadLocalRandom.current().nextInt(0, size) + ""
            );
        }

        return graph;
    }
}
