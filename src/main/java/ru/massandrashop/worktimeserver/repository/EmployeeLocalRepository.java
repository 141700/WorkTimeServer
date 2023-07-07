package ru.massandrashop.worktimeserver.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.massandrashop.worktimeserver.model.Employee;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@Component
@NoArgsConstructor
public class EmployeeLocalRepository {

    private ConcurrentHashMap<String, Employee> employeeLocalRepository = new ConcurrentHashMap<>();

}
