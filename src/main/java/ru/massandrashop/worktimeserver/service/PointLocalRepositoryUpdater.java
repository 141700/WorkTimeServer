package ru.massandrashop.worktimeserver.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.model.Point;
import ru.massandrashop.worktimeserver.repository.PointJpaRepository;
import ru.massandrashop.worktimeserver.repository.PointLocalRepository;
import ru.massandrashop.worktimeserver.service.startup.RunAtStartup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@Slf4j
public class PointLocalRepositoryUpdater {

    private final PointJpaRepository pointJpaRepository;

    private final PointLocalRepository pointLocalRepository;

    @Transactional
    @RunAtStartup
    @Scheduled(cron = "@midnight")
    public void updatePointLocalRepository() {
        ConcurrentHashMap<String, Point> map = pointJpaRepository.readAllByIdNotNull()
                .collect(Collectors.toMap(Point::getMacAddress,
                        entry -> entry,
                        (prev, next) -> next,
                        ConcurrentHashMap::new));
        pointLocalRepository.setPointLocalRepository(map);
        log.info(String.format("----------> Local point repository updated with %d records", map.size()));
    }

}
