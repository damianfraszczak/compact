/**
 *
 */
package pl.edu.wat.wcy.cop.services.repositories;

import org.springframework.data.repository.history.RevisionRepository;

import java.io.Serializable;

/**
 *
 * @author Damian Frąszczak
 * @since 16 lut 2017
 */
// Defines the contract for base audited repository.
public interface BaseAuditedRepository<T, ID extends Serializable>
        extends BaseRepository<T, Serializable>, RevisionRepository<T, ID, Integer> {

}
