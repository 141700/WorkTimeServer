package ru.massandrashop.worktimeserver.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.model.Employee;
import ru.massandrashop.worktimeserver.repository.EmployeeJpaRepository;
import ru.massandrashop.worktimeserver.repository.EmployeeLocalRepository;
import ru.massandrashop.worktimeserver.service.startup.RunAtStartup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeLocalRepositoryUpdater {

    private final EmployeeJpaRepository employeeJpaRepository;

    private final EmployeeLocalRepository employeeLocalRepository;

    @Transactional
    @RunAtStartup
    @Scheduled(cron = "@midnight")
    public void updateEmployeeLocalRepository() {
        ConcurrentHashMap<String, Employee> map = employeeJpaRepository.readAllByIdNotNull()
                .collect(Collectors.toMap(Employee::getDeviceId,
                        entry -> entry,
                        (prev, next) -> next,
                        ConcurrentHashMap::new));
        employeeLocalRepository.setEmployeeLocalRepository(map);
        log.info(String.format("----------> Local employee repository updated with %d records", map.size()));
    }

}
