package graph;

import java.util.*;

public class ConcreteEdgesGraph<T> implements Graph<T> {
    private final Set<T> vertices = new HashSet<>();
    private final List<Map.Entry<T, Map.Entry<T, Integer>>> edges = new ArrayList<>();

    @Override
    public boolean addVertex(T vertex) {
        return vertices.add(vertex);
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (!vertices.remove(vertex)) return false;
        edges.removeIf(edge -> edge.getKey().equals(vertex) || edge.getValue().getKey().equals(vertex));
        return true;
    }

    @Override
    public boolean addEdge(T source, T destination, int weight) {
        if (!vertices.contains(source) || !vertices.contains(destination)) return false;

        edges.removeIf(edge -> edge.getKey().equals(source) && edge.getValue().getKey().equals(destination));
        edges.add(new AbstractMap.SimpleEntry<>(source, new AbstractMap.SimpleEntry<>(destination, weight)));
        return true;
    }

    @Override
    public boolean removeEdge(T source, T destination) {
        return edges.removeIf(edge -> edge.getKey().equals(source) && edge.getValue().getKey().equals(destination));
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public Set<T> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<T, Integer> getEdges(T vertex) {
        Map<T, Integer> connectedEdges = new HashMap<>();
        for (Map.Entry<T, Map.Entry<T, Integer>> edge : edges) {
            if (edge.getKey().equals(vertex)) {
                connectedEdges.put(edge.getValue().getKey(), edge.getValue().getValue());
            }
        }
        return connectedEdges;
    }
}
