package pl.edu.wat.wcy.cop.app.shared.domain;
// Defines the contract for circle object.

public interface ICircleObject {
    GeoPointDto getPoint();

    void setPoint(GeoPointDto point);

    double getRadius();


    void setRadius(double radius);
}
