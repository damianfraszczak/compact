/**
 *
 */
package pl.edu.wat.wcy.cop.app.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.cop.app.server.EntityDtoConverter;
import pl.edu.wat.wcy.cop.domain.BaseEntity;
import pl.edu.wat.wcy.cop.services.IAbstractService;

import javax.validation.Valid;
// Handles abstract requests.


public abstract class AbstractController<T extends BaseEntity, D, S extends IAbstractService<T, Long>> {
    @Autowired
    protected EntityDtoConverter converter;
    @Autowired
    private S service;

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json",
            "application/xml"})
    public ResponseEntity<?> post(@RequestBody @Valid D dto) {
        dto = toDto(service.add(toEntity(dto)));
        return WebUtil.createOkEntity(dto);
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json",
            "application/xml"})
    public ResponseEntity<?> put(@RequestBody @Valid D dto) {
        dto = toDto(service.update(toEntity(dto)));
        return WebUtil.createOkEntity(dto);
    }

    @DeleteMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json",
            "application/xml"})
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.delete(id);
        return WebUtil.createOkEntity("");
    }

    /**
     * @param entity
     * @return
     */
    protected D toDto(T entity) {
        return converter.toDto(entity, getDtoClass());
    }

    /**
     * @param dto
     * @return
     */
    protected T toEntity(D dto) {
        return converter.toEntity(dto, getEntityClass());
    }

    /**
     * @return
     */
    protected abstract Class<D> getDtoClass();

    /**
     * @return
     */
    protected abstract Class<T> getEntityClass();
}
