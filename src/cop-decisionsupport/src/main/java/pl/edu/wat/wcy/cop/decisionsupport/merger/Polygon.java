package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
// Represents polygon.

public class Polygon implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Vertex> vertices = new LinkedList<>();
    private transient boolean lastWasHorizontal;

    public Polygon() {
        super();
    }

    public boolean add(Edge edge) {
        boolean isPolygonClosed = false;
        if (vertices.isEmpty()) {
            addInitial(edge);
        } else {
            isPolygonClosed = addAnother(edge);
        }
        return isPolygonClosed;
    }

    private boolean addAnother(Edge edge) {
        if (lastWasHorizontal == edge.isHorizontal()) {
            vertices.removeLast();
        } else {
            lastWasHorizontal = edge.isHorizontal();
        }
        if (edge.getEndingVertex().equals(vertices.getFirst())) {
            Vertex first = vertices.getFirst();
            Vertex second = vertices.get(1);
            Vertex last = vertices.getLast();
            if ((first.getX() == second.getX() && second.getX() == last.getX())
                    || (first.getY() == second.getY() && second.getY() == last.getY())) {
                vertices.removeFirst();
            }
            vertices.add(vertices.getFirst());
            return true;
        } else {
            vertices.add(edge.getEndingVertex());
        }
        return false;
    }

    private void addInitial(Edge edge) {
        vertices.add(edge.getStartingVertex());
        vertices.add(edge.getEndingVertex());
        lastWasHorizontal = edge.isHorizontal();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Polygon other = (Polygon) obj;
        if (vertices == null) {
            return other.vertices == null;
        } else return vertices.equals(other.vertices);
    }

}
