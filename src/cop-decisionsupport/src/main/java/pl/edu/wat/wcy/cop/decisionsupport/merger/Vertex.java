package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.awt.*;
import java.io.Serializable;
// Represents vertex.

public class Vertex implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Point point;
    private final VertexType vertexType;
    private final int x;
    private final int y;

    public Vertex(Point point, VertexType vertexType) {
        this.point = point;
        this.vertexType = vertexType;
        x = point.x + vertexType.getX();
        y = point.y + vertexType.getY();
    }

    @Override
    public String toString() {
        return "Vertex [" + getPoint() + ", " + getVertexType() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getX();
        result = prime * result + getY();
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
        Vertex other = (Vertex) obj;
        if (getX() != other.getX())
            return false;
        return getY() == other.getY();
    }

    public Point getPoint() {
        return point;
    }

    public VertexType getVertexType() {
        return vertexType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
