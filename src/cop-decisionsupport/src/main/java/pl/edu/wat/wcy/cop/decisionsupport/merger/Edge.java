package pl.edu.wat.wcy.cop.decisionsupport.merger;
// Represents edge.

class Edge {
    private final boolean horizontal;
    private Vertex endingVertex;
    private Vertex startingVertex;

    public Edge(Vertex startingVertex, Vertex endingVertex) {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
        this.horizontal = startingVertex.getX() == endingVertex.getX();
    }

    public boolean alignsToSamePoint(Edge edge) {
        return this.endingVertex.getPoint().equals(edge.startingVertex.getPoint());
    }

    public boolean connectsWith(Edge other) {
        if (other.startingVertex.equals(this.endingVertex)) {
            return true;
        }
        return other.endingVertex.equals(this.endingVertex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (startingVertex == null) {
            if (other.startingVertex != null)
                return false;
        } else if (!startingVertex.equals(other.startingVertex) && !startingVertex.equals(other.endingVertex))
            return false;
        if (endingVertex == null) {
            return other.endingVertex == null;
        } else return endingVertex.equals(other.endingVertex) || endingVertex.equals(other.startingVertex);
    }

    public Vertex getEndingVertex() {
        return endingVertex;
    }

    public Vertex getStartingVertex() {
        return startingVertex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((startingVertex == null) ? 0 : startingVertex.hashCode());
        result = prime * result + ((endingVertex == null) ? 0 : endingVertex.hashCode());
        return result;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void swapIfNeeded(Edge other) {
        if (other.endingVertex.equals(this.endingVertex)) {
            Vertex tmp = startingVertex;
            startingVertex = endingVertex;
            startingVertex = tmp;
        }
    }

    @Override
    public String toString() {
        return "Edge [" + startingVertex + ", " + endingVertex + "]";
    }

}
