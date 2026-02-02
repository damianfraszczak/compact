package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.awt.*;
import java.util.List;
import java.util.*;
// Represents merged points.

public class MergedPoints {
    private final Collection<Point> points = new HashSet<>();

    public MergedPoints(Point point) {
        points.add(point);
    }

    public MergedPoints merge(MergedPoints mergedPoints) {
        points.addAll(mergedPoints.points);
        return this;
    }

    public MergedPoints merge(Point point) {
        points.add(point);
        return this;
    }

    public List<Polygon> getPolygons() {
        List<Edge> edges = new ArrayList<>();
        for (Point point : points) {
            VertexType[] vertexTypes = VertexType.values();
            for (int i = 0; i < vertexTypes.length; i++) {
                Vertex vertex1 = new Vertex(point, vertexTypes[i]);
                Vertex vertex2 = new Vertex(point, vertexTypes[(i + 1) % 4]);
                Edge edge = new Edge(vertex1, vertex2);
                if (edges.contains(edge)) {
                    edges.remove(edge);
                } else {
                    edges.add(edge);
                }
            }
        }

        for (int i = 0; i < edges.size() - 1; i++) {
            int j = i + 2;
            Edge edge = edges.get(i);
            while (!edge.connectsWith(edges.get(i + 1)) && j < edges.size()) {
                Collections.swap(edges, i + 1, j++);
            }
            for (; j < edges.size(); j++) {
                if (edge.connectsWith(edges.get(j)) && edge.alignsToSamePoint(edges.get(j))) {
                    Collections.swap(edges, i + 1, j);
                }
            }
        }
        LinkedList<Polygon> polygons = new LinkedList<>();
        polygons.add(new Polygon());
        for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext(); ) {
            Edge edge = iterator.next();
            boolean polyClosed = polygons.getLast().add(edge);
            if (polyClosed && iterator.hasNext()) {
                polygons.add(new Polygon());
            }
        }
        return polygons;
    }

    @Override
    public String toString() {
        return "MergedPoints [points=" + Arrays.toString(points.toArray()) + "]";
    }

}
