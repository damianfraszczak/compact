/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Defines the contract for symbol on the map with code.


public interface ISymbolOnTheMapWithCode<K> extends ISymbolOnTheMap<K> {

    String getCode();
}
