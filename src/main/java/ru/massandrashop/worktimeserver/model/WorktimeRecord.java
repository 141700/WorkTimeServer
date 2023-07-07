package ru.massandrashop.worktimeserver.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class WorktimeRecord {

    private final Office office;

    private final Employee employee;

    private final LocalDateTime start;

    @Setter
    private LocalDateTime finish;

    public WorktimeRecord(Office office, Employee employee, LocalDateTime start) {
        this.office = office;
        this.employee = employee;
        this.start = start;
    }

    public Duration getDuartion() {
        return finish != null ? Duration.between(start, finish) : Duration.ZERO.plusHours(1L);
    }

}
