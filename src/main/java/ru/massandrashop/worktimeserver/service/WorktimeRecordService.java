package ru.massandrashop.worktimeserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.model.Record;
import ru.massandrashop.worktimeserver.model.WorktimeRecord;
import ru.massandrashop.worktimeserver.repository.WorktimeRecordsJpaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WorktimeRecordService {

    private final WorktimeRecordsJpaRepository worktimeRecordsJpaRepository;

    private static final DateTimeFormatter WORKING_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    @Transactional
    public TreeMap<String, List<WorktimeRecord>> getOfficeWorktimeRecords(LocalDateTime begin, LocalDateTime end) {
        TreeMap<String, List<WorktimeRecord>> officeRecords = new TreeMap<>();
        getAllWorktimeRecords(begin, end).values()
                .forEach(wtr -> officeRecords.merge(wtr.getOffice().getTitle(),
                        new ArrayList<>() {{
                            add(wtr);
                        }},
                        (oldValue, newValue) -> {
                            oldValue.add(wtr);
                            return oldValue;
                        }));
        officeRecords.values().forEach(list -> list.sort(Comparator.comparing(WorktimeRecord::getStart)));
        return officeRecords;
    }

    private TreeMap<String, WorktimeRecord> getAllWorktimeRecords(LocalDateTime begin, LocalDateTime end) {
        TreeMap<String, WorktimeRecord> allRecords = new TreeMap<>();
        worktimeRecordsJpaRepository.findByTimeStampBetween(begin, end)
                .sorted(Comparator.comparing(Record::getId))
                .forEachOrdered(entry -> processRecord(allRecords, entry));
        return allRecords;
    }

    private void processRecord(Map<String, WorktimeRecord> allRecords, Record record) {
        LocalDateTime timeStamp = record.getTimeStamp();
        allRecords.merge(generateKey(record),
                new WorktimeRecord(record.getOffice(), record.getEmployee(), timeStamp),
                (oldValue, newValue) -> {
                    oldValue.setFinish(timeStamp);
                    return oldValue;
                });
    }

    private String generateKey(Record record) {
        return record.getTimeStamp().format(WORKING_DATE_FORMATTER)
                + "/" + record.getEmployee().getId();
    }

}
