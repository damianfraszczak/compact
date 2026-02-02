package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.List;
// Represents polygon with height.

public class PolygonWithHeight {
    private Integer height;
    private List<List<Double>> coordinates;

    public PolygonWithHeight() {

    }

    public PolygonWithHeight(Integer height, List<List<Double>> coordinates) {
        super();
        this.height = height;
        this.coordinates = coordinates;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

}
