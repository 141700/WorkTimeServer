package ru.massandrashop.worktimeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.massandrashop.worktimeserver.model.Office;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface OfficeJpaRepository extends JpaRepository<Office, Long> {

    Stream<Office> readAllByIdNotNull();

    Optional<Office> findById(Long id);

}
