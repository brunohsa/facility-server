package br.com.facility.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return Objects.nonNull(locDate) ? Date.valueOf(locDate) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return Objects.nonNull(sqlDate) ? sqlDate.toLocalDate() :  null;
    }
}
