package de.bht.s68161.gvis;

import de.bht.s68161.gvis.graph.Node;
import de.bht.s68161.gvis.graph.VisualizedGraph;
import de.bht.s68161.gvis.graph.VisualizedMST;
import de.bht.s68161.gvis.graph.WeightedEdge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GraphVisualizer {

    private static final int SLEEP_DURATION = 2000;

    public static void main(String[] args) throws InterruptedException {
        // initExampleGraph();
        initExampleKruskal();
    }

    private static void initExampleGraph() throws InterruptedException {
        VisualizedGraph vGraph = new VisualizedGraph();
        VisualizedMST vMST = new VisualizedMST(vGraph);

        Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"}).forEach(vGraph::addNode);

        vGraph.addEdge("eh", "E", "H", 6);
        vGraph.addEdge("ga", "F", "A", 7);
        vGraph.addEdge("ch", "C", "H", 9);
        WeightedEdge ab = vGraph.addEdge("ab", "A", "B", 3);
        WeightedEdge ae = vGraph.addEdge("ae", "A", "E", 4);
        WeightedEdge bf = vGraph.addEdge("bf", "B", "F", 2);
        WeightedEdge di = vGraph.addEdge("di", "D", "I", 5);
        WeightedEdge fi = vGraph.addEdge("fi", "F", "I", 4);
        WeightedEdge hi = vGraph.addEdge("hi", "H", "I", 3);
        WeightedEdge gf = vGraph.addEdge("gf", "G", "F", 1);
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
    }

    private static void initExampleKruskal() throws InterruptedException {

        VisualizedGraph vGraph = new VisualizedGraph();
        VisualizedMST vMST = new VisualizedMST(vGraph, 1000);

        Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"}).forEach(vGraph::addNode);

        vGraph.addEdge("ab", "A", "B", 3);
        vGraph.addEdge("ae", "A", "E", 4);
        vGraph.addEdge("bf", "B", "F", 2);
        vGraph.addEdge("ch", "C", "H", 9);
        vGraph.addEdge("di", "D", "I", 5);
        vGraph.addEdge("ec", "E", "C", 8);
        vGraph.addEdge("eh", "E", "H", 6);
        vGraph.addEdge("fi", "F", "I", 4);
        vGraph.addEdge("ga", "F", "A", 7);
        vGraph.addEdge("gf", "G", "F", 1);
        vGraph.addEdge("hi", "H", "I", 3);

        kruskal(vGraph, vMST);
    }

    private static HashSet<WeightedEdge> kruskal(VisualizedGraph graph, VisualizedMST mst) {
        HashSet<HashSet<Node>> nodeClusters = new HashSet<>();
        graph.getNodes().forEach(node -> {
            HashSet<Node> cluster = new HashSet<>();
            cluster.add(node);
            nodeClusters.add(cluster);
        });

        List<WeightedEdge> edges = graph
                .getEdges()
                .stream()
                .sorted(Comparator.comparingInt(WeightedEdge::getWeight))
                .collect(Collectors.toList());

        for (WeightedEdge edge : edges) {
            Node nodeA = edge.getNodeA();
            Node nodeB = edge.getNodeB();

            HashSet<Node> clusterA = findContainingCluster(nodeClusters, nodeA);
            HashSet<Node> clusterB = findContainingCluster(nodeClusters, nodeB);

            if(clusterA != clusterB) {
                HashSet<Node> merged = new HashSet<>();
                merged.addAll(clusterA);
                merged.addAll(clusterB);
                nodeClusters.remove(clusterA);
                nodeClusters.remove(clusterB);
                nodeClusters.add(merged);
                mst.addEdgeToMST(edge);
            }
        }

        return mst.getEdges();
    }

    private static HashSet<Node> findContainingCluster(HashSet<HashSet<Node>> nodeClusters, Node node) {
        return nodeClusters.stream().filter(cluster -> cluster.contains(node)).findAny().get();
    }
}
