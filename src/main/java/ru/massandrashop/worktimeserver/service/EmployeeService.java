package ru.massandrashop.worktimeserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.model.Employee;
import ru.massandrashop.worktimeserver.repository.EmployeeJpaRepository;
import ru.massandrashop.worktimeserver.repository.EmployeeLocalRepository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeLocalRepository employeeLocalRepository;

    private final EmployeeJpaRepository employeeJpaRepository;

    public Employee getEmployee(String name, String deviceId) {
        ConcurrentHashMap<String, Employee> localRepository = employeeLocalRepository.getEmployeeLocalRepository();
        return localRepository.computeIfAbsent(deviceId, k -> findOrCreateEmployee(name, deviceId));
    }

    public Employee findOrCreateEmployee(String name, String deviceId) {
        return findByDeviceId(deviceId)
                .orElse(createAndSaveEmployee(name, deviceId));
    }

    @Transactional
    private Employee createAndSaveEmployee(String name, String deviceId) {
        return employeeJpaRepository.save(new Employee(name, deviceId));
    }

    @Transactional
    private Optional<Employee> findByDeviceId(String deviceId) {
        return employeeJpaRepository.findByDeviceId(deviceId);
    }

}