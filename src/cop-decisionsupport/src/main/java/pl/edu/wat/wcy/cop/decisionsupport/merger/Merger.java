package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
// Represents merger.

public abstract class Merger {

    protected int[][] keys;
    protected Map<Integer, MergedPoints> map = new HashMap<>();
    protected int nextKey = 1;
    private int rows;
    private int cols;

    public Merger(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.keys = new int[rows][cols];
    }

    public List<Polygon> getPolygons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Point point = PointFactory.createPoint(i, j);
                if (isValid(point)) {
                    processPoint(point);
                }
            }
        }
        Set<MergedPoints> mergedPointsCollection = new HashSet<>();
        for (Entry<Integer, MergedPoints> entry : map.entrySet()) {
            mergedPointsCollection.add(entry.getValue());
        }
        List<Polygon> polys = new ArrayList<>();
        for (MergedPoints mergedPoints : mergedPointsCollection) {
            polys.addAll(mergedPoints.getPolygons());
        }
        return polys;
    }

    private void processPoint(Point point) {
        int x = point.x;
        int y = point.y;
        boolean isTopValid = isValid(Direction.TOP.move(point));
        boolean isLeftValid = isValid(Direction.LEFT.move(point));
        if (isTopValid) {
            MergedPoints topMergedPoints = map.get(keys[x - 1][y]);
            topMergedPoints.merge(point);
            keys[x][y] = keys[x - 1][y];
            if (isLeftValid) {
                MergedPoints leftMergedPoints = map.get(keys[x][y - 1]);
                topMergedPoints.merge(leftMergedPoints);
                for (Entry<Integer, MergedPoints> entry : map.entrySet()) {
                    if (entry.getValue().equals(leftMergedPoints)) {
                        map.put(entry.getKey(), topMergedPoints);
                    }
                }
            }
        } else if (isLeftValid) {
            MergedPoints leftMergedPoints = map.get(keys[x][y - 1]);
            leftMergedPoints.merge(point);
            keys[x][y] = keys[x][y - 1];
        } else {
            int key = nextKey++;
            keys[x][y] = key;
            map.put(key, new MergedPoints(point));
        }
    }

    public abstract boolean isValid(Point point);
}
