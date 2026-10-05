/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

/**
 * @param <K>
 *            object key type
 */
// Defines the contract for unique object.
public interface UniqueObject<K> {

    K getKey();

    String getDescription();
}
