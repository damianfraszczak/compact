/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Defines the contract for symbol on the map.


public interface ISymbolOnTheMap<K> extends UniqueObject<K> {

    MapSymbolDto getMapSymbol();

    GeoPointDto getPoint();

    String getName();

    String getDescription();

}
