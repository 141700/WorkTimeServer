package ru.massandrashop.worktimeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.massandrashop.worktimeserver.model.Record;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Repository
public interface WorktimeRecordsJpaRepository extends JpaRepository<Record, Long> {

    Stream<Record> findByTimeStampBetween(LocalDateTime begin, LocalDateTime end);

}