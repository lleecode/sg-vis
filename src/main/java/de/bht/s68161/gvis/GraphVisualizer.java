package de.bht.s68161.gvis;

import de.bht.s68161.gvis.graph.VisualizedGraph;
import de.bht.s68161.gvis.graph.VisualizedMST;
import de.bht.s68161.gvis.graph.WeightedEdge;

import java.util.Arrays;

public class GraphVisualizer {

    private static final int SLEEP_DURATION = 2000;

    public static void main(String[] args) throws InterruptedException {
        initVisualizedGraph();
    }

    private static void initVisualizedGraph() throws InterruptedException {
        VisualizedGraph vGraph = new VisualizedGraph();
        VisualizedMST vMST = new VisualizedMST(vGraph);

        Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"}).forEach(vGraph::addNode);

        WeightedEdge ab = vGraph.addEdge("ab", "A", "B", 3);
        WeightedEdge ae = vGraph.addEdge("ae", "A", "E", 4);
        WeightedEdge bf = vGraph.addEdge("bf", "B", "F", 2);
        vGraph.addEdge("ch", "C", "H", 9);
        WeightedEdge di = vGraph.addEdge("di", "D", "I", 5);
        vGraph.addEdge("eh", "E", "H", 6);
        WeightedEdge fi = vGraph.addEdge("fi", "F", "I", 4);
        WeightedEdge hi = vGraph.addEdge("hi", "H", "I", 3);
        WeightedEdge gf = vGraph.addEdge("gf", "G", "F", 1);
        vGraph.addEdge("ga", "F", "A", 7);
        WeightedEdge ec = vGraph.addEdge("ec", "E", "C", 8);

        Thread.sleep(SLEEP_DURATION);

        vMST.addEdgeToMST(ab);
        vMST.addEdgeToMST(ae);
        vMST.addEdgeToMST(bf);
        vMST.addEdgeToMST(di);
        vMST.addEdgeToMST(fi);
        vMST.addEdgeToMST(hi);
        vMST.addEdgeToMST(gf);
        vMST.addEdgeToMST(ec);

        Thread.sleep(SLEEP_DURATION);

        vMST.getEdges().forEach(e -> System.out.println(""
                + "Edge: " + e.getName()
                + " between " + e.getNodeA().getName()
                + " and " + e.getNodeB().getName())
        );
    }
}
