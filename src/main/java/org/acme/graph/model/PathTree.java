package org.acme.graph.model;

import java.util.*;

public class PathTree {

    private Map<Vertex, PathNode> nodes = new HashMap<>();
    public PathTree(Graph graph, Vertex origin){
        for (Vertex vertex : graph.getVertices()) {
            PathNode pathNode = new PathNode(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY, null, false);
            nodes.put(vertex, pathNode);
        }
    }

    public Path getPath(Vertex destination){
        List<Edge> result = new ArrayList<>();

        Edge current = getNode(destination).getReachingEdge();
        do {
            result.add(current);
            current = getNode(current.getSource()).getReachingEdge();
        } while (current != null);

        Collections.reverse(result);
        return new Path(result);
    }

    public PathNode getNode(Vertex vertex){
        return this.nodes.get(vertex);
    }

}
