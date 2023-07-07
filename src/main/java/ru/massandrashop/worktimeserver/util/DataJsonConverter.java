package ru.massandrashop.worktimeserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.massandrashop.worktimeserver.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class DataJsonConverter implements AttributeConverter<Employee, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Employee employee) {
        try {
            return objectMapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert to Json", e);
        }
    }

    @Override
    public Employee convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Employee.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert from Json", e);
        }
    }

}
