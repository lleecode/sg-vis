package de.bht.s68161.gvis;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class GraphVisualizer {

    private static final int SLEEP_DURATION = 50;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.graphstream.ui", "swing");
        displayGraphWithMST();
    }

    private static void displayRandomGraph(int size) throws InterruptedException {

        SingleGraph graph = new SingleGraph("0", false, false);

        graph.setAttribute("ui.stylesheet", "url('file:./src/main/resources/graph.css')");

        graph.display();

        for (int i = 0; i < size; i++) {
            Thread.sleep(SLEEP_DURATION);
            graph.addNode("" + i);
        }
        for (int i = 0; i < size; i++) {
            Thread.sleep(SLEEP_DURATION);
            Edge e = graph.addEdge(
                    i + "",
                    ThreadLocalRandom.current().nextInt(0, size) + "",
                    ThreadLocalRandom.current().nextInt(0, size) + ""
            );
            if (e != null) {
                if (i % 3 == 2) e.setAttribute("ui.class", "one");
                else if (i % 3 == 1) e.setAttribute("ui.class", "two");
            }
        }
        for (int i = 0; i < graph.getEdgeCount(); i++) {
            Thread.sleep(SLEEP_DURATION);
            graph.getEdge(i).setAttribute("ui.fill-color", "red");
        }
    }

    private static void displayGraphWithMST() throws InterruptedException {
        SingleGraph graph = new SingleGraph("1", false, false);
        graph.setAttribute("ui.stylesheet", "url('file:./src/main/resources/graphWithMST.css')");
        graph.display();

        // Add nodes A through I to graph
        Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"}).forEach(graph::addNode);

        for (Node n: graph) {
            n.setAttribute("ui.label", n.getId());
        }

        graph.addEdge("ab", "A", "B").setAttribute("ui.label", 3);
        graph.addEdge("ae", "A", "E").setAttribute("ui.label", 4);
        graph.addEdge("bf", "B", "F").setAttribute("ui.label", 2);
        graph.addEdge("ch", "C", "H").setAttribute("ui.label", 9);
        graph.addEdge("di", "D", "I").setAttribute("ui.label", 5);
        graph.addEdge("eh", "E", "H").setAttribute("ui.label", 6);
        graph.addEdge("fi", "F", "I").setAttribute("ui.label", 4);
        graph.addEdge("hi", "H", "I").setAttribute("ui.label", 3);
        graph.addEdge("gf", "G", "F").setAttribute("ui.label", 1);
        graph.addEdge("ga", "F", "A").setAttribute("ui.label", 7);
        graph.addEdge("ec", "E", "C").setAttribute("ui.label", 8);

        Thread.sleep(10000);

        graph.getEdge("gf").setAttribute("ui.class", "mst");
        graph.getEdge("bf").setAttribute("ui.class", "mst");
        graph.getEdge("ab").setAttribute("ui.class", "mst");
        graph.getEdge("fi").setAttribute("ui.class", "mst");
        graph.getEdge("di").setAttribute("ui.class", "mst");
        graph.getEdge("hi").setAttribute("ui.class", "mst");
        graph.getEdge("ae").setAttribute("ui.class", "mst");
        graph.getEdge("ec").setAttribute("ui.class", "mst");


    }
}
