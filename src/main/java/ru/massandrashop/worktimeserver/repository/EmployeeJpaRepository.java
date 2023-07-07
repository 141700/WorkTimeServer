package ru.massandrashop.worktimeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.massandrashop.worktimeserver.model.Employee;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Stream<Employee> readAllByIdNotNull();

    Optional<Employee> findByDeviceId(String deviceId);

}
