/**
 *
 */
package pl.edu.wat.wcy.cop.app.server;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.PropertyInfo;
import org.springframework.stereotype.Component;


@Component
// Converts entity dto data.
public class EntityDtoConverter {

    public <D, E> E toEntity(D dto, Class<E> entityClass) {
        return this.toEntity(dto, entityClass, false);
    }

    public <D, E> E toEntity(D dto, Class<E> entityClass, boolean skipId) {
        ModelMapper modelMapper = new ModelMapper();
        if (skipId) {
            Condition skipIds = new Condition() {

                @Override
                public boolean applies(MappingContext context) {
                    PropertyInfo propertyInfo = context.getMapping().getLastDestinationProperty();
                    return !(propertyInfo.getName().equals("id") || propertyInfo.getName().equals("uuid"));
                }
            };
            modelMapper.getConfiguration().setPropertyCondition(skipIds);
        }

        return modelMapper.map(dto, entityClass);
    }

    public <E, D> D toDto(E entity, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }
}
