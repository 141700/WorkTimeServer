package ru.massandrashop.worktimeserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.massandrashop.worktimeserver.model.Point;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PointJpaRepository extends JpaRepository<Point, Long> {

    Stream<Point> readAllByIdNotNull();

    Optional<Point> findByMacAddress(String macAddress);

}
