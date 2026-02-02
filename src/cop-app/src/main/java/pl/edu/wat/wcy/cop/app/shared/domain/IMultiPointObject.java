package pl.edu.wat.wcy.cop.app.shared.domain;

import java.util.List;
// Defines the contract for multi point object.

public interface IMultiPointObject {
    List<GeoPointDto> getPoints();

    void setPoints(List<GeoPointDto> points);
}
