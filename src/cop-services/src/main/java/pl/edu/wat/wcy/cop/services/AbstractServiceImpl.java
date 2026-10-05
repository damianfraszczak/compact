/**
 *
 */
package pl.edu.wat.wcy.cop.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wat.wcy.cop.domain.BaseEntity;
import pl.edu.wat.wcy.cop.services.repositories.BaseRepository;
// Represents abstract service impl.

public abstract class AbstractServiceImpl<T extends BaseEntity, R extends BaseRepository<T, Long>>
        implements IAbstractService<T, Long> {

    @Autowired
    protected R repository;

    @Override
    public T add(T object) {
        T result = repository.save(object);
        return result;
    }

    @Override
    public T update(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    /* (non-Javadoc)
     * @see pl.edu.wat.wcy.cop.services.IAbstractService#delete(java.io.Serializable)
     */
    @Override
    public void delete(Long id) {
        repository.delete(id);

    }
}
