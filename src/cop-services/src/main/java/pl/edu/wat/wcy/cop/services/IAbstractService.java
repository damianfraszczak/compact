/**
 *
 */
package pl.edu.wat.wcy.cop.services;

import java.io.Serializable;
// Defines the contract for abstract service.

public interface IAbstractService<T, ID extends Serializable> {

    T add(T object);

    T update(T object);

    void delete(T object);

    void delete(ID id);
}
