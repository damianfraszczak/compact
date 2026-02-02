package pl.edu.wat.wcy.cop.decisionsupport.merger;
// Enumerates vertex type.

public enum VertexType {
    LEFTTOP(1, 0), LEFTBOTTOM(0, 0), RIGHTBOTTOM(0, 1), RIGHTTOP(1, 1);

    private final int x;
    private final int y;

    VertexType(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
