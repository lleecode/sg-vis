package de.bht.s68161.gvis;

import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.concurrent.ThreadLocalRandom;

public class GraphVisualizer {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.graphstream.ui", "swing");
        displayRandomGraph(10);
    }

    private static void displayRandomGraph(int size) throws InterruptedException {

        SingleGraph graph = new SingleGraph("0", false, false);

        graph.setAttribute("ui.stylesheet", "url('file:./src/main/resources/graph.css')");

        graph.display();

        for (int i = 0; i < size; i++) {
            Thread.sleep(200);
            graph.addNode("" + i);
        }
        for (int i = 0; i < size; i++) {
            Thread.sleep(200);
            Edge e = graph.addEdge(
                    i + "",
                    ThreadLocalRandom.current().nextInt(0, size) + "",
                    ThreadLocalRandom.current().nextInt(0, size) + ""
            );
            if (e != null) {
                if (i % 2 == 0) e.setAttribute("ui.class", "one");
                else e.setAttribute("ui.class", "two");
            }
        }
        for (int i = 0; i < graph.getEdgeCount(); i++) {
            Thread.sleep(200);
            graph.getEdge(i).setAttribute("ui.fill-color", "red");
        }
    }
}
