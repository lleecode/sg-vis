package de.bht.s68161.gvis;

import de.bht.s68161.gvis.graph.VisualizedGraph;
import de.bht.s68161.gvis.graph.VisualizedMST;
import de.bht.s68161.gvis.graph.WeightedEdge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
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
        VisualizedMST vMST = new VisualizedMST(vGraph);

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
    }

    private static HashSet<WeightedEdge> kruskal(VisualizedGraph graph, VisualizedMST mst) {

        return mst.getEdges();
    }

/**
 *
 * TODO: re-implement kruskal
 *
    private static void kruskal() {

        // collection for resulting minimum spanning tree
        Graph<Vertex, Edge<Vertex>> mst = new Graph<>(false);
        // init empty collection for mst edges
        HashSet<Edge<Vertex>> mstEdges = new HashSet<>();
        // init empty set of sets which each contain a connected cluster of vertices
        HashSet<HashSet<Vertex>> clusteredVertices = new HashSet<>();
        // add all vertices to mst
        graph.getVertices().forEach(mst::addVertex);

        // init cluster collection
        graph.getVertices().forEach(v -> {
            HashSet<Vertex> cluster = new HashSet<>();
            cluster.add(v);
            clusteredVertices.add(cluster);
        });

        // sort edges by weight
        List<Edge<Vertex>> sortedEdges = graph
                .getEdges()
                .stream()
                .sorted(Comparator.comparingInt(Edge::getWeight))
                .collect(Collectors.toList());

        // iterate over all edges in ascending order of weight
        for (Edge<Vertex> edge : sortedEdges) {
            // get vertices
            Vertex vertexA = edge.getVertexA();
            Vertex vertexB = edge.getVertexB();

            // find containing cluster for both vertices
            HashSet<Vertex> bContainer = findContainingCluster(clusteredVertices, vertexB);
            HashSet<Vertex> aContainer = findContainingCluster(clusteredVertices, vertexA);

            if (aContainer != bContainer) {
                // create union
                HashSet<Vertex> merged = new HashSet<>();
                merged.addAll(aContainer);
                merged.addAll(bContainer);
                // replace clusters with union
                clusteredVertices.remove(aContainer);
                clusteredVertices.remove(bContainer);
                clusteredVertices.add(merged);
                // add edge to mst
                mstEdges.add(edge);
            }
        }
        mstEdges.forEach(mst::addEdge); // collect edges in mst graph
        return mst;
    }

    private static HashSet<Vertex> findContainingCluster(HashSet<HashSet<Vertex>> container, Vertex vertex) {
        return container.stream().filter(cluster -> cluster.contains(vertex)).findAny().get();
    }
    **/
}
