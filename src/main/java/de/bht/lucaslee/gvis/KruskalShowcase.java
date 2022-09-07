package de.bht.lucaslee.gvis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalShowcase {

    public static void main(String[] args) throws InterruptedException {
        WeightedGraph weightedGraph = createWeightedGraph();
        VisualizedSubgraph visualizedSubgraph = new VisualizedSubgraph(weightedGraph);
        kruskal(weightedGraph, visualizedSubgraph);
    }

    private static WeightedGraph createWeightedGraph() {
        WeightedGraph weightedGraph = new WeightedGraph();

        Arrays.stream(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"})
                .forEach(weightedGraph::addNode);

        weightedGraph.addEdge("ab", "A", "B", 3);
        weightedGraph.addEdge("ae", "A", "E", 4);
        weightedGraph.addEdge("bf", "B", "F", 2);
        weightedGraph.addEdge("ch", "C", "H", 9);
        weightedGraph.addEdge("di", "D", "I", 5);
        weightedGraph.addEdge("ec", "E", "C", 8);
        weightedGraph.addEdge("eh", "E", "H", 6);
        weightedGraph.addEdge("fi", "F", "I", 4);
        weightedGraph.addEdge("ga", "F", "A", 7);
        weightedGraph.addEdge("gf", "G", "F", 1);
        weightedGraph.addEdge("hi", "H", "I", 3);

        return weightedGraph;
    }

    private static void kruskal(WeightedGraph graph, VisualizedSubgraph visualizedSubgraph)
            throws InterruptedException {
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

            if (clusterA != clusterB) {
                HashSet<Node> merged = new HashSet<>();
                merged.addAll(clusterA);
                merged.addAll(clusterB);
                nodeClusters.remove(clusterA);
                nodeClusters.remove(clusterB);
                nodeClusters.add(merged);
                visualizedSubgraph.addEdgeToSubgraph(edge);
            }
        }
    }

    private static HashSet<Node> findContainingCluster(
            HashSet<HashSet<Node>> nodeClusters,
            Node node
    ) {
        return nodeClusters.stream().filter(cluster -> cluster.contains(node)).findAny().get();
    }
}
