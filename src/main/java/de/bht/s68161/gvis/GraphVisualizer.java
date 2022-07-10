package de.bht.s68161.gvis;

import org.graphstream.graph.implementations.SingleGraph;

import java.util.concurrent.ThreadLocalRandom;

public class GraphVisualizer {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.graphstream.ui", "swing");
        displayRandomGraph(10);
    }


    private static void displayRandomGraph(int size) throws InterruptedException {

        SingleGraph graph = new SingleGraph("0", false, false);

        graph.display();

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
    }


}
