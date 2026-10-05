/**
 *
 */
package pl.edu.wat.wcy.cop.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 *
 * @author Damian Frąszczak
 * @since 16 lut 2017
 */
// Defines the contract for base repository.
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
