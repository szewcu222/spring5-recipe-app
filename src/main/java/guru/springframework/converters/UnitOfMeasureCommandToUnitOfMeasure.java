package guru.springframework.converters;

import guru.springframework.dtos.UnitOfMeasureDTO;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureDTO, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureDTO source) {
        if (Objects.isNull(source)) {
            return null;
        }
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        //uom.setIngredient(source.getIngredient());
        return uom;
    }
}
