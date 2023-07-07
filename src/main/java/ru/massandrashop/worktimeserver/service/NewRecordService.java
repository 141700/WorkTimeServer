package ru.massandrashop.worktimeserver.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.model.Employee;
import ru.massandrashop.worktimeserver.model.Office;
import ru.massandrashop.worktimeserver.model.Record;
import ru.massandrashop.worktimeserver.repository.WorktimeRecordsJpaRepository;

@RequiredArgsConstructor
@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NewRecordService {

    WorktimeRecordsJpaRepository recordsRepository;

    EmployeeService employeeService;

    PointService pointService;

    @Transactional
    public void addNewRecord(String name, String deviceId, String macAddress) {
        Employee employee = employeeService.getEmployee(name, deviceId);
        Office office = pointService.getOffice(macAddress);
        if (office != null && employee != null) {
            recordsRepository.save(new Record(office, employee));
        }
    }

}